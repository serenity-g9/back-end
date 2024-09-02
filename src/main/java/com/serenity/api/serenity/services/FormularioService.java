package com.serenity.api.serenity.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.serenity.api.serenity.models.formulario.Questao;
import com.serenity.api.serenity.models.formulario.Resposta;
import com.serenity.api.serenity.models.formulario.RespostaUsuario;
import com.serenity.api.serenity.utils.SortUtil;

import java.io.FileReader;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FormularioService {

    private static final String SECRET_PATH = "secret";
    private static final String CREDENCIAIS_JSON = SECRET_PATH + "/credenciais.json";
    private static final List<String> ESCOPOS = List.of("https://www.googleapis.com/auth/drive");
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static Credential getCredential() throws IOException, GeneralSecurityException {
        NetHttpTransport netHttpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GoogleClientSecrets googleClientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new FileReader(CREDENCIAIS_JSON));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                netHttpTransport, JSON_FACTORY, googleClientSecrets, ESCOPOS)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(SECRET_PATH)))
                .setAccessType("offline")
                .build();

        Credential credential = flow.loadCredential("user");
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8081).build();

        if (credential != null && (credential.getRefreshToken() != null || credential.getExpiresInSeconds() > 60)) return credential;

        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public static List<RespostaUsuario> getRespostas(String idForm) {
        String url = "https://forms.googleapis.com/v1/forms/" + idForm + "/responses";

        try {
            String respostaJson = getJsonResponse(url);
            JsonNode respostaNode = MAPPER.readTree(respostaJson);

            List<RespostaUsuario> respostaUsuarios = new ArrayList<>();
            for (JsonNode response : respostaNode.path("responses")) {

                List<Resposta> respostas = new ArrayList<>();
                for (JsonNode answer : response.path("answers")) {

                    Set<String> valores = new HashSet<>();
                    for (JsonNode value : answer.path("textAnswers").path("answers")) {
                        valores.add(value.path("value").asText());
                    }

                    Resposta resposta = new Resposta(
                            answer.path("questionId").asText(),
                            valores
                    );

                    respostas.add(resposta);
                }

                respostaUsuarios.add(new RespostaUsuario(
                        response.path("responseId").asText(),
                        LocalDateTime.parse(response.path("lastSubmittedTime").asText()),
                        respostas
                ));
            }

            return SortUtil.selectionSort(respostaUsuarios.toArray(new RespostaUsuario[0]));
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Questao> getQuestoes(String idForm) {
        String url = "https://forms.googleapis.com/v1/forms/" + idForm;

        try {
            String respostaJson = getJsonResponse(url);
            JsonNode respostaNode = MAPPER.readTree(respostaJson);

            List<Questao> questoes = new ArrayList<>();
            for (JsonNode item : respostaNode.path("items")) {
                questoes.add(
                        new Questao(
                                item.path("questionItem").path("question").path("questionId").asText(),
                                item.path("title").asText()
                        )
                );
            }

            return SortUtil.selectionSort(questoes.toArray(new Questao[0]));

        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getJsonResponse(String url) throws GeneralSecurityException, IOException {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        HttpRequestFactory requestFactory = httpTransport.createRequestFactory(getCredential());

        return requestFactory.buildGetRequest(new GenericUrl(url)).execute().parseAsString();
    }
}

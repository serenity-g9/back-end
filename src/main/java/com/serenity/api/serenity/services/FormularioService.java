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
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.serenity.api.serenity.dtos.formulario.Questao;
import com.serenity.api.serenity.dtos.formulario.Resposta;
import com.serenity.api.serenity.dtos.formulario.RespostaUsuario;
import com.serenity.api.serenity.exceptions.NaoEncontradoException;
import com.serenity.api.serenity.models.Formulario;
import com.serenity.api.serenity.repositories.FormularioRepository;
import com.serenity.api.serenity.utils.SortUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.GeneralSecurityException;
import java.time.OffsetDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class FormularioService {

    private final FormularioRepository formularioRepository;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static HttpRequestFactory getRequestFactory() throws IOException {
        GoogleCredentials credentials = GoogleCredentials
                .fromStream(new ByteArrayInputStream(System.getenv("GOOGLE_FORMS_API_CREDENTIALS").getBytes()))
                .createScoped(List.of("https://www.googleapis.com/auth/forms.responses.readonly", "https://www.googleapis.com/auth/forms.body.readonly"));

        return new NetHttpTransport().createRequestFactory(new HttpCredentialsAdapter(credentials));
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
                        OffsetDateTime.parse(response.path("lastSubmittedTime").asText()).toLocalDateTime(),
                        respostas
                ));
            }

            RespostaUsuario[] v = respostaUsuarios.toArray(new RespostaUsuario[0]);
            SortUtil.quickSort(v, 0, v.length-1);
            return Arrays.stream(v).toList();
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
        HttpRequestFactory requestFactory = getRequestFactory();
        return requestFactory.buildGetRequest(new GenericUrl(url)).execute().parseAsString();
    }

    public Formulario cadastrar(Formulario formulario) {
        return formularioRepository.save(formulario);
    }

    public List<Formulario> listar() {
        return formularioRepository.findAll();
    }

    public Formulario buscarPorId(UUID id) {
        return formularioRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("formulário"));
    }

    public void deletar(UUID id) {
        buscarPorId(id);
        formularioRepository.softDeleteById(id);
    }

    public Formulario atualizar(UUID id, Formulario formulario) {
        buscarPorId(id);
        formulario.setId(id);

        return formularioRepository.save(formulario);
    }
}

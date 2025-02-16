package com.serenity.api.serenity.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.serenity.api.serenity.dtos.formulario.*;
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

    public List<Formulario> integrarForms() {
        List<GoogleForm> googleForms = getGoogleForms();
        List<Formulario> forms = new ArrayList<>();

        for (GoogleForm googleForm : googleForms) {
            Formulario formulario = new Formulario();
            formulario.setId(googleForm.getId());
            formulario.setNome(googleForm.getNome());

            forms.add(formulario);
        }

        return formularioRepository.saveAll(forms);
    }

    public static HttpRequestFactory getRequestFactory() throws IOException {
        GoogleCredentials credentials = GoogleCredentials
                .fromStream(new ByteArrayInputStream(System.getenv("GOOGLE_FORMS_API_CREDENTIALS").getBytes()))
                .createScoped(List.of("https://www.googleapis.com/auth/forms.responses.readonly", "https://www.googleapis.com/auth/forms.body.readonly", "https://www.googleapis.com/auth/drive.metadata.readonly"));

        return new NetHttpTransport().createRequestFactory(new HttpCredentialsAdapter(credentials));
    }

    public static List<RespostaUsuario> getRespostas(String idForm) {
        String url = "https://forms.googleapis.com/v1/forms/" + idForm + "/responses";

        try {
            String jsonResponse = getJsonResponse(url);
            JsonNode node = MAPPER.readTree(jsonResponse);

            List<RespostaUsuario> respostaUsuarios = new ArrayList<>();
            for (JsonNode response : node.path("responses")) {

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

    public Map<String, Object> getRespostasPorQuestao(String idForm) {
        Formulario formulario = buscarPorId(idForm);

        List<Questao> questoes = getQuestoes(idForm);
        List<RespostaUsuario> respostas = getRespostas(idForm);

        List<QuestaoRespostas> questoesRespostas = new ArrayList<>();

        for (Questao questao : questoes) {
            QuestaoRespostas questaoRespostas = new QuestaoRespostas(
                    questao,
                    new ArrayList<RespostaQuestao>()
            );

            for (RespostaUsuario respostaUsuario : respostas) {
                for (Resposta resposta : respostaUsuario.getRespostas()) {
                    if (resposta.getIdQuestao().equals(questao.getId())) {
                        questaoRespostas.getRespostas().add(new RespostaQuestao(
                                respostaUsuario.getId(),
                                resposta.getValores(),
                                respostaUsuario.getHorarioEnviado()
                        ));
                    }
                }
            }

            questoesRespostas.add(questaoRespostas);
        }

        Map<String, Object> questoesRespostasMap = new HashMap<>();
        questoesRespostasMap.put("formulario", formulario.getNome());
        questoesRespostasMap.put("questoes", questoesRespostas);
        return questoesRespostasMap;
    }

    public static List<Questao> getQuestoes(String idForm) {
        String url = "https://forms.googleapis.com/v1/forms/" + idForm;

        try {
            String jsonResponse = getJsonResponse(url);
            JsonNode node = MAPPER.readTree(jsonResponse);

            List<Questao> questoes = new ArrayList<>();
            for (JsonNode item : node.path("items")) {
                JsonNode questionNode = item.path("questionItem").path("question");

                String tipo;
                if (questionNode.has("choiceQuestion")) {
                    tipo = questionNode.path("choiceQuestion").path("type").asText();
                } else if (questionNode.has("dateQuestion")) {
                    tipo = "DATE";
                } else if (questionNode.has("timeQuestion")) {
                    tipo = "TIME";
                } else if (questionNode.has("textQuestion")) {
                    tipo = "TEXT";
                } else {
                    tipo = "UNKNOWN";
                }

                questoes.add(new Questao(
                        questionNode.path("questionId").asText(),
                        item.path("title").asText(),
                        tipo
                ));
            }

            return SortUtil.selectionSort(questoes.toArray(new Questao[0]));

        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<GoogleForm> getGoogleForms() {
        String url = "https://www.googleapis.com/drive/v3/files?q=mimeType='application/vnd.google-apps.form'&fields=files(id,name)";
        try {
            String jsonResponse = getJsonResponse(url);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(jsonResponse);
            List<GoogleForm> forms = new ArrayList<>();

            if (root.has("files")) {
                for (JsonNode file : root.get("files")) {
                    String id = file.has("id") ? file.get("id").asText() : "";
                    String name = file.has("name") ? file.get("name").asText() : "";
                    forms.add(new GoogleForm(id, name));
                }
            }

            return forms;
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

    public Formulario buscarPorId(String id) {
        return formularioRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("formulário"));
    }

    public void deletar(String id) {
        buscarPorId(id);
        formularioRepository.softDeleteById(id);
    }

    public Formulario atualizar(String id, Formulario formulario) {
        buscarPorId(id);
        formulario.setId(id);

        return formularioRepository.save(formulario);
    }
}

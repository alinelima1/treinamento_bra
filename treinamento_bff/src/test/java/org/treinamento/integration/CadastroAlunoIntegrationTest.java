package org.treinamento.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.maciejwalkowiak.wiremock.spring.ConfigureWireMock;
import com.maciejwalkowiak.wiremock.spring.EnableWireMock;
import com.maciejwalkowiak.wiremock.spring.InjectWireMock;
import feign.FeignException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.treinamento.common.file.FileUtil;
import org.treinamento.dto.ResponseDTO;
import org.treinamento.dto.AlunoDTO;
import org.treinamento.service.CadastroAlunoService;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@ActiveProfiles("test")
@SpringBootTest
@EnableWireMock({
        @ConfigureWireMock(
                name = "wiremock-server",
                port = 8280)
})
public class CadastroAlunoIntegrationTest {
    @InjectWireMock("wiremock-server")
    protected WireMockServer wiremock;

    @Autowired
    private CadastroAlunoService cadastroAlunoService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void baseBeforeEach() {
        wiremock.resetAll();
    }

    @Test
    @DisplayName("1 - Obter aluno através do seu número de matrícula")
    public void testObterAluno_200() {
       String expectedResponse = FileUtil.readFile("integration/response/response_200.json");

       wiremock.stubFor(
                WireMock.get(urlPathEqualTo("/aluno"))
                        .withQueryParam("matricula", equalTo("150"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(expectedResponse)));

        ResponseEntity<ResponseDTO> response = cadastroAlunoService.buscarAluno("150");

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("Retorno sucesso.", response.getBody().mensagem());
        Assertions.assertEquals("150", response.getBody().content().get(0).matricula());
        Assertions.assertEquals("Eduardo Ribeiro", response.getBody().content().get(0).nomeAluno());
    }

    @Test
    @DisplayName("2 - Buscar por uma matrícula não existente")
    public void testObterAluno_400() {
        String responseBody = FileUtil.readFile("integration/response/response_400.json");

        wiremock.stubFor(
                WireMock.get(urlPathEqualTo("/aluno"))
                        .withQueryParam("matricula", equalTo("151"))
                        .willReturn(aResponse()
                                .withStatus(400)
                                .withHeader("Content-Type", "application/json")
                                .withBody(responseBody)));

        FeignException exception =
                Assertions.assertThrows(
                        FeignException.class,
                        () -> cadastroAlunoService.buscarAluno("151"));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), exception.status());
        Assertions.assertTrue(exception.contentUTF8().contains("Não foi possível localizar um aluno com esta matrícula"));

    }

   @Test
    @DisplayName("3 - Erro ao tentar salvar aluno com dados inválidos")
    public void testSalvarAluno_400() throws Exception {
        String requestBody = FileUtil.readFile("integration/request/request_salvar_aluno_erro.json");

        String responseBody = FileUtil.readFile("integration/response/response_salvar_aluno_erro.json");

       AlunoDTO aluno = objectMapper.readValue(requestBody, AlunoDTO.class);

        wiremock.stubFor(
                WireMock.post(urlPathEqualTo("/aluno/salvar"))
                        .withRequestBody(equalToJson(requestBody))
                        .willReturn(aResponse()
                                .withStatus(400)
                                .withHeader("Content-Type", "application/json")
                                .withBody(responseBody)));

       FeignException exception =
                Assertions.assertThrows(
                        FeignException.class,
                        () -> cadastroAlunoService.salvarAluno(aluno));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), exception.status());
        Assertions.assertTrue(exception.contentUTF8().contains("Não foi possível salvar o aluno, verifique os dados e tente novamente."));
   }

    @Test
    @DisplayName("4 - Salvar aluno com sucesso")
    public void testSalvarAluno_201() throws Exception {
        String requestBody = FileUtil.readFile("integration/request/request_salvar_aluno_success.json");

        String responseBody = FileUtil.readFile("integration/response/response_salvar_aluno_success.json");

        AlunoDTO aluno = objectMapper.readValue(requestBody, AlunoDTO.class);

        wiremock.stubFor(
                WireMock.post(urlPathEqualTo("/aluno/salvar"))
                        .withRequestBody(equalToJson(requestBody))
                        .willReturn(aResponse()
                                .withStatus(201)
                                .withHeader("Content-Type", "application/json")
                                .withBody(responseBody)));

        ResponseEntity<ResponseDTO> response = cadastroAlunoService.salvarAluno(aluno);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("Aluno salvo com sucesso.", response.getBody().mensagem());
        Assertions.assertEquals("180", response.getBody().content().get(0).matricula());
        Assertions.assertEquals("Aline Abreu", response.getBody().content().get(0).nomeAluno());
    }
}
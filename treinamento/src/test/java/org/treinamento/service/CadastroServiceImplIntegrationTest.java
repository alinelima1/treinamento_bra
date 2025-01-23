package org.treinamento.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.file.FileUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.treinamento.dto.ResponseDTO;
import org.treinamento.model.Aluno;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql(
        scripts = "classpath:sql/data.sql",
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class CadastroServiceImplIntegrationTest {

    @Autowired
    private CadastroService cadastroService;

    @Autowired private ObjectMapper objectMapper;

    @Test
    public void buscarAlunoPelaMatricula_Sucesso() throws Exception {
        String matricula = "070";
        ResponseDTO response = cadastroService.buscarAluno(matricula);

        Optional<Aluno> primeiroAluno = response.content().stream().findFirst();

        Assertions.assertEquals(matricula, primeiroAluno.get().getMatricula());
    }

    @Test
    public void buscarAlunoPelaMatricula_AlunoInexistente() {
        Exception exception =
                Assertions.assertThrows(
                        Exception.class,
                        () -> cadastroService.buscarAluno("900"));

        Assertions.assertEquals("Não foi possível localizar um aluno com esta matrícula", exception.getMessage());
    }

    @Test
    public void salvarAluno_Sucesso() throws Exception {
        String requestBodyJson = FileUtil.readFile("./integration/new_aluno_request.json");

        Aluno novoAluno = objectMapper.readValue(requestBodyJson, Aluno.class);

        ResponseDTO response = cadastroService.salvarAluno(novoAluno);

        Optional<Aluno> alunoResponse = response.content().stream().findFirst();

        assertEquals(alunoResponse.get().getMatricula(), novoAluno.getMatricula());
        assertEquals(alunoResponse.get().getNomeAluno(), novoAluno.getNomeAluno());
        assertEquals("Aluno salvo com sucesso.", response.mensagem());
    }

    @Test
    public void salvarAluno_DadosInvalidos() throws Exception {
        String requestBodyJson = FileUtil.readFile("./integration/new_aluno_request_error.json");

        Aluno aluno = objectMapper.readValue(requestBodyJson, Aluno.class);

        Exception exception =
                Assertions.assertThrows(
                        Exception.class,
                        () -> cadastroService.salvarAluno(aluno));

        Assertions.assertEquals("Não foi possível salvar o aluno, verifique os dados e tente novamente.", exception.getMessage());
    }
}

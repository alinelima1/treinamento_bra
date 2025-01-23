package org.treinamento.service;

import org.treinamento.dto.ResponseDTO;
import org.treinamento.model.Aluno;

public interface CadastroService {
    ResponseDTO buscarAluno(String matricula) throws Exception;

    ResponseDTO buscarAlunos(int page, int pageSize);

    ResponseDTO salvarAluno(Aluno aluno) throws Exception;
}

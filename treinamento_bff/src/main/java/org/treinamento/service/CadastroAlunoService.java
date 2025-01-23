package org.treinamento.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.treinamento.dto.ResponseDTO;
import org.treinamento.feign.CadastroAlunoFeign;
import org.treinamento.dto.AlunoDTO;

@Service
public class CadastroAlunoService {

    private final CadastroAlunoFeign cadastroAlunoFeign;

    public CadastroAlunoService(CadastroAlunoFeign cadastroAlunoFeign) {
        this.cadastroAlunoFeign = cadastroAlunoFeign;
    }

    public ResponseEntity<ResponseDTO> buscarAluno(String matricula){
        return cadastroAlunoFeign.buscarAluno(matricula);
    }

    public ResponseEntity<ResponseDTO> listarAlunos(int page, int pageSize){
        return cadastroAlunoFeign.buscarAlunos(page, pageSize);
    }

    public ResponseEntity<ResponseDTO> salvarAluno(AlunoDTO aluno) throws Exception {
        return cadastroAlunoFeign.salvarAluno(aluno);
    }
}

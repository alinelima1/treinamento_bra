package org.treinamento.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.treinamento.dto.AlunoDTO;
import org.treinamento.dto.ResponseDTO;
import org.treinamento.service.CadastroAlunoService;

import java.util.List;

@RestController
public class CadastroAlunoController {

    private final CadastroAlunoService cadastroAlunoService;

    public CadastroAlunoController(CadastroAlunoService cadastroAlunoService) {
        this.cadastroAlunoService = cadastroAlunoService;
    }

    @GetMapping(value = "cadaluno/buscar")
    public ResponseEntity<ResponseDTO> buscarAluno(@RequestParam String matricula){
        return cadastroAlunoService.buscarAluno(matricula);
    }

    @GetMapping(value = "cadaluno/listar")
    public ResponseEntity<ResponseDTO> listarAlunos(@RequestParam(required = false) int page,
                                                       @RequestParam(required = false) int pageSize){
        return cadastroAlunoService.listarAlunos(page, pageSize);
    }

    @PostMapping(value = "cadaluno/salvar")
    public ResponseEntity<ResponseDTO> salvarAluno(@RequestBody AlunoDTO aluno) throws Exception {
        return cadastroAlunoService.salvarAluno(aluno);
    }
}
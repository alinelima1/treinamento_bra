package org.treinamento.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.treinamento.controller.CadastroController;
import org.treinamento.dto.ResponseDTO;
import org.treinamento.model.Aluno;
import org.treinamento.service.CadastroService;

import java.util.ArrayList;

@RestController
public class CadastroControllerImpl implements CadastroController {

    private final CadastroService cadastroService;

    public CadastroControllerImpl(CadastroService cadastroService){
        this.cadastroService = cadastroService;
    }

    @Override
    public ResponseEntity<ResponseDTO> buscarAluno(@RequestParam(value = "matricula") String matricula) {
        try{
            return ResponseEntity.ok(this.cadastroService.buscarAluno(matricula));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("0", e.getMessage(), new ArrayList<Aluno>()));
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> buscarAlunos(@RequestParam(required = false) int page,
                                                       @RequestParam(required = false) int pageSize) {
        try{
            return ResponseEntity.ok(this.cadastroService.buscarAlunos(page, pageSize));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("0", e.getMessage(), new ArrayList<Aluno>()));
        }

    }

    @Override
    public ResponseEntity<ResponseDTO> salvarAluno(Aluno aluno) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(this.cadastroService.salvarAluno(aluno));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("0", e.getMessage(), new ArrayList<Aluno>()));
        }
    }
}

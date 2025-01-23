package org.treinamento.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.treinamento.dto.AlunoDTO;
import org.treinamento.dto.ResponseDTO;

import java.util.List;

@FeignClient(value = "treinamento", url = "${url.treinamento}")
public interface CadastroAlunoFeign {

    @GetMapping("/aluno")
    ResponseEntity<ResponseDTO> buscarAluno(@RequestParam("matricula") String matricula);

    @GetMapping("/aluno/lista")
    ResponseEntity<ResponseDTO> buscarAlunos(@RequestParam("page") int page,
                                                @RequestParam("pageSize") int pageSize);

    @PostMapping("/aluno/salvar")
    ResponseEntity<ResponseDTO> salvarAluno(@RequestBody AlunoDTO aluno) throws Exception;
}
package org.treinamento.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.treinamento.dto.ResponseDTO;
import org.treinamento.model.Aluno;

@Tag(name="Aluno", description="Cadastro e consulta de alunos na base de dados.")
@RequestMapping
public interface CadastroController {
    @Operation(
            operationId = "buscarAluno",
            summary="Endpoint responsável pela consulta de um único aluno.",
            description="Endpoint responsável pela consulta de um único aluno."
    )
    @GetMapping()
    ResponseEntity<ResponseDTO> buscarAluno(@RequestParam(value = "matricula") String matricula);

    @Operation(
            operationId = "buscarAlunos",
            summary="Endpoint responsável pela consulta de vários alunos.",
            description="Endpoint responsável pela consulta de vários alunos."
    )
    @GetMapping("/lista")
    ResponseEntity<ResponseDTO> buscarAlunos(@RequestParam(required = false) int page,
                                                @RequestParam(required = false) int pageSize);

    @Operation(
            operationId = "salvarAluno",
            summary="Endpoint responsável por salvar um aluno",
            description="Endpoint responsável por salvar um aluno"
            )
    @ApiResponse(responseCode = "201", description = "Sucesso", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class))
    })
    @ApiResponse(responseCode = "400", description = "Erro ao salvar o aluno", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class))
    })
    @PostMapping("/salvar")
    ResponseEntity<ResponseDTO> salvarAluno(@RequestBody Aluno aluno) throws Exception;
}
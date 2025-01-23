package org.treinamento.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record AlunoDTO(

        @NotBlank(message="Matrícula é obrigatória")
        String matricula,
        @NotBlank(message="Nome do aluno é obrigatório")
        String nomeAluno,
        @NotBlank(message="Endereço é obrigatório")
        String endereco,
        @NotBlank(message="CPF é obrigatório")
        String cpf,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate dataMatricula)
{
}

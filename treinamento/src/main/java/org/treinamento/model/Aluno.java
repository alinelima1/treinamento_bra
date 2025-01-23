package org.treinamento.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import org.treinamento.config.TrimmedStringTypeConfig;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "aluno")
public class Aluno{
        @Id
        @Type(value = TrimmedStringTypeConfig.class)
        @Column(name = "matricula", nullable = false, length = 3, columnDefinition = "char(3)")
        String matricula;
        @Type(value = TrimmedStringTypeConfig.class)
        @Column(name = "naluno", nullable = false, length = 30, columnDefinition = "char(30)")
        String nomeAluno;
        @Type(value = TrimmedStringTypeConfig.class)
        @Column(name = "endereco", nullable = false, length = 30, columnDefinition = "char(30)")
        String endereco;
        @Type(value = TrimmedStringTypeConfig.class)
        @Column(name = "cpf", nullable = false, length = 11, columnDefinition = "char(11)")
        String cpf;
        @Column(name = "dmatricula")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate dataMatricula;
}


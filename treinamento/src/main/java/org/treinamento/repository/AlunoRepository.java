package org.treinamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.treinamento.model.Aluno;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, String> {
    Aluno findByMatricula(@Param("matricula") String matricula);
}

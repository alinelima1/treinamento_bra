package org.treinamento.dto;

import org.treinamento.model.Aluno;
import java.util.List;

public record ResponseDTO (String totalPaginas,
                          String  mensagem,
                          List<Aluno> content){}

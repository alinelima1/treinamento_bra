package org.treinamento.dto;

import java.util.List;

public record ResponseDTO(String totalPaginas,
                          String  mensagem,
                          List<AlunoDTO> content){}

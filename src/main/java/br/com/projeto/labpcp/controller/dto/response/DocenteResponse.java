package br.com.projeto.labpcp.controller.dto.response;

import java.util.Date;

public record DocenteResponse(Long id,
                              String nome,
                              Date dataEntrada,
                              Long idUsuario
) {
}

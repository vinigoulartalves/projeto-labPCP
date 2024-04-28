package br.com.projeto.labpcp.controller.dto.request;

import java.util.Date;

public record InserirDocenteRequest(Long id,
                                    String nome,
                                    Date dataEntrada) {
}

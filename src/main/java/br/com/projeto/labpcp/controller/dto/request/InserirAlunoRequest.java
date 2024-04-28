package br.com.projeto.labpcp.controller.dto.request;

import java.util.Date;

public record InserirAlunoRequest(String nome,
                                  Date dataNascimento) {
}

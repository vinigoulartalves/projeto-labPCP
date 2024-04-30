package br.com.projeto.labpcp.controller.dto.request;

import java.util.Date;

public record AlterarAlunoRequest(String nome, Date dataNascimento) {
}

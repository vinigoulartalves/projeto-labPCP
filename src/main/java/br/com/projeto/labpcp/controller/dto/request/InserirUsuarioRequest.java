package br.com.projeto.labpcp.controller.dto.request;

public record InserirUsuarioRequest(
        String login,
        String senha,
        String nomePapel) {
}

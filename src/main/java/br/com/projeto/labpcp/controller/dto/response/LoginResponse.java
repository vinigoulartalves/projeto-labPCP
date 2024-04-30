package br.com.projeto.labpcp.controller.dto.response;

public record LoginResponse(String valorJWT, long tempoExpiracao) {
}

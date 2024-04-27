package br.com.projeto.labpcp.service;

import br.com.projeto.labpcp.controller.dto.request.LoginRequest;
import br.com.projeto.labpcp.controller.dto.response.LoginResponse;
import br.com.projeto.labpcp.datasource.entity.UsuarioEntity;
import br.com.projeto.labpcp.datasource.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenService {

    private static long TEMPO_EXPIRACAO = 36000L;
    private final BCryptPasswordEncoder bCryptEncoder;
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDencoder;
    private final UsuarioRepository usuarioRepository;

    public LoginResponse gerarToken(
            @RequestBody LoginRequest loginRequest
    ) {

        UsuarioEntity usuarioEntity = usuarioRepository
                .findByLogin(loginRequest.login())
                .orElseThrow(
                        () -> {
                            log.error("Erro, usuário não existe");
                            return new RuntimeException("Erro, usuário não existe");
                        }
                );

        if (!usuarioEntity.senhaValida(loginRequest, bCryptEncoder)) {
            log.error("Erro, senha incorreta");
            throw new RuntimeException("Erro, senha incorreta");
        }


        Instant now = Instant.now();

        String scope = usuarioEntity.getPapel().getNome();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("SERVIDOR")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(TEMPO_EXPIRACAO))
                .subject(usuarioEntity.getId().toString())
                .claim("scope", scope)
                .build();

        var valorJWT = jwtEncoder.encode(
                        JwtEncoderParameters.from(claims))
                .getTokenValue();

        return new LoginResponse(valorJWT, TEMPO_EXPIRACAO);


    }

    public String buscaCampo(String token, String claim) {
        return jwtDencoder
                .decode(token)
                .getClaims()
                .get(claim)
                .toString();
    }
}


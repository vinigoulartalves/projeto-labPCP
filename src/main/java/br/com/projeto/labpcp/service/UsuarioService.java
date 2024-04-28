package br.com.projeto.labpcp.service;

import br.com.projeto.labpcp.controller.dto.request.InserirUsuarioRequest;
import br.com.projeto.labpcp.datasource.entity.UsuarioEntity;
import br.com.projeto.labpcp.datasource.repository.PapelRepository;
import br.com.projeto.labpcp.datasource.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final BCryptPasswordEncoder bCryptEncoder;
    private final UsuarioRepository usuarioRepository;
    private final PapelRepository perfilRepository;

    public void cadastraNovoUsuario(
            @RequestBody InserirUsuarioRequest inserirUsuarioRequest
    ) {
        boolean usuarioExiste = usuarioRepository
                .findByLogin(inserirUsuarioRequest.login())
                .isPresent();

        if (usuarioExiste) {
            throw new RuntimeException("Usuario já existe");
        }


        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setLogin(inserirUsuarioRequest.login());
        usuario.setSenha(bCryptEncoder.encode(inserirUsuarioRequest.senha()));
        usuario.setPapel(
                perfilRepository.findByNome(inserirUsuarioRequest.nomePapel())
                        .orElseThrow(() -> new RuntimeException("Perfil inválido ou inexistente"))
        );

        usuarioRepository.save(usuario);
    }

}

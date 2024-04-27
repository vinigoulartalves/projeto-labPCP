package br.com.projeto.labpcp.controller;


import br.com.projeto.labpcp.controller.dto.request.InserirUsuarioRequest;
import br.com.projeto.labpcp.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CadastroController {

    private final UsuarioService usuarioService;

    @PostMapping("/cadastro")
    public ResponseEntity<String> novoUsuario(
            @Validated @RequestBody InserirUsuarioRequest inserirUsuarioRequest
    ) {

        usuarioService.cadastraNovoUsuario(inserirUsuarioRequest);

        return ResponseEntity.ok("Usuario Salvo!");
    }
}

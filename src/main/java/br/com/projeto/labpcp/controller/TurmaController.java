package br.com.projeto.labpcp.controller;

import br.com.projeto.labpcp.controller.dto.request.InserirAlunoRequest;
import br.com.projeto.labpcp.controller.dto.request.InserirTurmaRequest;
import br.com.projeto.labpcp.controller.dto.response.AlunoResponse;
import br.com.projeto.labpcp.controller.dto.response.TurmaResponse;
import br.com.projeto.labpcp.service.AlunoService;
import br.com.projeto.labpcp.service.TurmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("turmas")
public class TurmaController {

    private final TurmaService turmaService;

    @PostMapping
    public ResponseEntity<TurmaResponse> salvarTurma(@RequestHeader(name = "Authorization") String token,
                                                     @RequestBody InserirTurmaRequest inserirTurmaRequest) {
        return ResponseEntity.ok(turmaService.salvar(inserirTurmaRequest, token.substring(7)));
    }

}

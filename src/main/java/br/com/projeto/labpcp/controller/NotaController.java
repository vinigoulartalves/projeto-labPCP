package br.com.projeto.labpcp.controller;


import br.com.projeto.labpcp.controller.dto.request.AlterarNotaRequest;
import br.com.projeto.labpcp.controller.dto.request.InserirNotaRequest;
import br.com.projeto.labpcp.controller.dto.response.NotaResponse;
import br.com.projeto.labpcp.datasource.entity.NotaEntity;
import br.com.projeto.labpcp.service.NotaService;
import br.com.projeto.labpcp.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("notas")
public class NotaController {

    private final NotaService notaService;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<NotaResponse> salvarNota(@RequestHeader(name = "Authorization") String token, @RequestBody InserirNotaRequest inserirNotaRequest) {


        NotaResponse novoNota = notaService.salvar(inserirNotaRequest, token.substring(7));
        return ResponseEntity.status(HttpStatus.CREATED).body(novoNota);
    }

    @GetMapping
    public ResponseEntity<List<NotaEntity>> buscarTodasNotas(@RequestHeader(name = "Authorization") String token) {

        List<NotaEntity> notas = notaService.buscarTodos(token.substring(7));
        return ResponseEntity.ok(notas);
    }

    @GetMapping("{id}")
    public ResponseEntity<NotaEntity> buscarNotaPorId(@PathVariable Long id, @RequestHeader(name = "Authorization") String token) {


        NotaEntity notaEntity = notaService.buscarPorId(id, token.substring(7));
        return ResponseEntity.ok(notaEntity);

    }

    @PutMapping("{id}")
    public ResponseEntity<NotaEntity> alterarNota(@PathVariable Long id, @RequestBody AlterarNotaRequest alterarNotaRequest, @RequestHeader(name = "Authorization") String token) {


        NotaEntity notaAtualizada = notaService.alterar(id, alterarNotaRequest, token.substring(7));
        return ResponseEntity.ok(notaAtualizada);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> excluirNota(@PathVariable Long id, @RequestHeader(name = "Authorization") String token) {

        notaService.excluir(id, token.substring(7));
        return ResponseEntity.status(HttpStatus.OK).body("Entidade excluída com sucesso!");

    }

    @GetMapping("{id}/media-final")
    public ResponseEntity<Double> getMediaFinal(@PathVariable Long id, @RequestHeader(name = "Authorization") String token) {

        List<NotaEntity> notaEntity = (List<NotaEntity>) notaService.buscarPorId(id, token.substring(7));
        if (notaEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        BigDecimal mediaFinal = notaService.buscarMediaFinal(notaEntity);
        return ResponseEntity.ok(mediaFinal.doubleValue());
    }


}

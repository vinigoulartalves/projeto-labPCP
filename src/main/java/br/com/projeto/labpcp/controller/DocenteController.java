package br.com.projeto.labpcp.controller;

import br.com.projeto.labpcp.controller.dto.request.AlterarDocenteRequest;
import br.com.projeto.labpcp.controller.dto.request.InserirDocenteRequest;
import br.com.projeto.labpcp.controller.dto.response.DocenteResponse;
import br.com.projeto.labpcp.datasource.entity.DocenteEntity;
import br.com.projeto.labpcp.service.DocenteService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("docentes")
public class DocenteController {

    private final DocenteService docenteService;


    @GetMapping
    public ResponseEntity<List<DocenteEntity>> buscarTodosDocentes(@RequestHeader(name = "Authorization") String token) {
        List<DocenteEntity> docentes = docenteService.buscarTodos(token.substring(7));

        return ResponseEntity.status(HttpStatus.OK).body(docentes);
    }

    @GetMapping("{id}")
    public ResponseEntity<DocenteEntity> buscarDocentesPorId(@RequestHeader(name = "Authorization") String token,
                                                             @PathVariable Long id) {
        DocenteEntity docenteEntity = docenteService.buscarPorId(id, token.substring(7));
        return ResponseEntity.status(HttpStatus.OK).body(docenteEntity);
    }

    @PostMapping
    public ResponseEntity<DocenteResponse> salvarDocente(@RequestHeader(name = "Authorization") String token,
                                                         @RequestBody InserirDocenteRequest inserirDocenteRequest) {

        return ResponseEntity.ok(docenteService.salvar(inserirDocenteRequest, token.substring(7)));
    }

    @PutMapping("{id}")
    public ResponseEntity<DocenteEntity> alterarDocente(@RequestHeader(name = "Authorization") String token,
                                                        @PathVariable Long id,
                                                        @RequestBody AlterarDocenteRequest alterarDocenteRequest) {

        DocenteEntity docenteAlterado = docenteService.alterar(id, alterarDocenteRequest, token.substring(7));
        return ResponseEntity.status(HttpStatus.OK).body(docenteAlterado);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> excluirDocente(@RequestHeader(name = "Authorization") String token, @PathVariable Long id) {
        docenteService.excluirPorId(id, token.substring(7));
        return ResponseEntity.status(HttpStatus.OK).body("Entidade excluída com sucesso!");
    }

}

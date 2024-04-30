package br.com.projeto.labpcp.controller;

import br.com.projeto.labpcp.controller.dto.request.AlterarTurmaRequest;
import br.com.projeto.labpcp.controller.dto.request.InserirAlunoRequest;
import br.com.projeto.labpcp.controller.dto.request.InserirTurmaRequest;
import br.com.projeto.labpcp.controller.dto.response.AlunoResponse;
import br.com.projeto.labpcp.controller.dto.response.TurmaResponse;
import br.com.projeto.labpcp.datasource.entity.TurmaEntity;
import br.com.projeto.labpcp.datasource.entity.TurmaEntity;
import br.com.projeto.labpcp.datasource.entity.TurmaEntity;
import br.com.projeto.labpcp.service.AlunoService;
import br.com.projeto.labpcp.service.TurmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("turmas")
public class TurmaController {

    private final TurmaService turmaService;


    @GetMapping
    public ResponseEntity<List<TurmaEntity>> buscarTodosTurmas(@RequestHeader(name = "Authorization") String token) {
        List<TurmaEntity> turmas = turmaService.buscarTodos(token.substring(7));

        return ResponseEntity.status(HttpStatus.OK).body(turmas);
    }

    @GetMapping("{id}")
    public ResponseEntity<TurmaEntity> buscarTurmasPorId(@RequestHeader(name = "Authorization") String token,
                                                         @PathVariable Long id) {
        TurmaEntity turmaEntity = turmaService.buscarPorId(id, token.substring(7));
        return ResponseEntity.status(HttpStatus.OK).body(turmaEntity);
    }

    @PostMapping
    public ResponseEntity<TurmaResponse> salvarTurma(@RequestHeader(name = "Authorization") String token,
                                                     @RequestBody InserirTurmaRequest inserirTurmaRequest) {
        return ResponseEntity.ok(turmaService.salvar(inserirTurmaRequest, token.substring(7)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> excluirTurma(@RequestHeader(name = "Authorization") String token, @PathVariable Long id) {
        turmaService.excluirPorId(id, token.substring(7));
        return ResponseEntity.status(HttpStatus.OK).body("Entidade excluída com sucesso!");
    }

    @PutMapping("{id}")
    public ResponseEntity<TurmaEntity> alterarTurma(@RequestHeader(name = "Authorization") String token,
                                                    @PathVariable Long id,
                                                    @RequestBody AlterarTurmaRequest alterarTurmaRequest) {

        TurmaEntity turmaAlterado = turmaService.alterar(id, alterarTurmaRequest, token.substring(7));
        return ResponseEntity.status(HttpStatus.OK).body(turmaAlterado);

    }


}

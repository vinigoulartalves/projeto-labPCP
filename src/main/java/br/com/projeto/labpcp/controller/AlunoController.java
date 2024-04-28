package br.com.projeto.labpcp.controller;


import br.com.projeto.labpcp.controller.dto.request.AlterarAlunoRequest;
import br.com.projeto.labpcp.controller.dto.request.AlterarDocenteRequest;
import br.com.projeto.labpcp.controller.dto.request.InserirAlunoRequest;
import br.com.projeto.labpcp.controller.dto.response.AlunoResponse;
import br.com.projeto.labpcp.datasource.entity.AlunoEntity;
import br.com.projeto.labpcp.datasource.entity.DocenteEntity;
import br.com.projeto.labpcp.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("alunos")
public class AlunoController {

    private final AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<AlunoEntity>> buscarTodosAlunos(@RequestHeader(name = "Authorization") String token) {
        List<AlunoEntity> alunos = alunoService.buscarTodos(token.substring(7));

        return ResponseEntity.status(HttpStatus.OK).body(alunos);
    }

    @GetMapping("{id}")
    public ResponseEntity<AlunoEntity> buscarAlunosPorId(@RequestHeader(name = "Authorization") String token,
                                                         @PathVariable Long id) {
        AlunoEntity alunoEntity = alunoService.buscarPorId(id, token.substring(7));
        return ResponseEntity.status(HttpStatus.OK).body(alunoEntity);
    }

    @PostMapping
    public ResponseEntity<AlunoResponse> salvarAluno(@RequestHeader(name = "Authorization") String token,
                                                     @RequestBody InserirAlunoRequest inserirAlunoRequest) {
        return ResponseEntity.ok(alunoService.salvar(inserirAlunoRequest, token.substring(7)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> excluirAluno(@RequestHeader(name = "Authorization") String token, @PathVariable Long id) {
        alunoService.excluirPorId(id, token.substring(7));
        return ResponseEntity.status(HttpStatus.OK).body("Entidade excluída com sucesso!");
    }

    @PutMapping("{id}")
    public ResponseEntity<AlunoEntity> alterarAluno(@RequestHeader(name = "Authorization") String token,
                                                    @PathVariable Long id,
                                                    @RequestBody AlterarAlunoRequest alterarAlunoRequest) {

        AlunoEntity alunoAlterado = alunoService.alterar(id, alterarAlunoRequest, token.substring(7));
        return ResponseEntity.status(HttpStatus.OK).body(alunoAlterado);

    }


}

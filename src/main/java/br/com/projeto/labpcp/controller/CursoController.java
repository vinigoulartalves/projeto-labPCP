package br.com.projeto.labpcp.controller;

import br.com.projeto.labpcp.controller.dto.request.AlterarCursoRequest;
import br.com.projeto.labpcp.controller.dto.request.InserirCursoRequest;
import br.com.projeto.labpcp.controller.dto.response.CursoResponse;
import br.com.projeto.labpcp.datasource.entity.CursoEntity;
import br.com.projeto.labpcp.service.CursoService;
import br.com.projeto.labpcp.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("cursos")
public class CursoController {

    private final CursoService cursoService;
    private final TokenService tokenService;


    @PostMapping
    public ResponseEntity<CursoResponse> criarCurso(@RequestBody InserirCursoRequest inserirCursoRequest,
                                                    @RequestHeader(name = "Authorization") String token) {

        return ResponseEntity.ok(cursoService.salvar(inserirCursoRequest, token.substring(7)));
    }

    @GetMapping
    public ResponseEntity<List<CursoEntity>> buscarTodosCursos(@RequestHeader(name = "Authorization") String token) {

        List<CursoEntity> cursos = cursoService.buscarTodos(token.substring(7));
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("{id}")
    public ResponseEntity<CursoEntity> buscarCursosPorId(@PathVariable Long id, @RequestHeader(name = "Authorization") String token) {

        CursoEntity cursoEntity = cursoService.buscarPorId(id, token.substring(7));
        return ResponseEntity.status(HttpStatus.OK).body(cursoEntity);

    }

    @PutMapping("{id}")
    public ResponseEntity<CursoEntity> auterarCurso(@PathVariable Long id, @RequestBody AlterarCursoRequest atualizarCursoRequest, @RequestHeader(name = "Authorization") String token) {

        CursoEntity cursoAtualizado = cursoService.alterar(id, atualizarCursoRequest, token.substring(7));

        return ResponseEntity.ok(cursoAtualizado);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> excluirCurso(@PathVariable Long id, @RequestHeader(name = "Authorization") String token) {


        cursoService.excluirPorId(id, token.substring(7));
        return ResponseEntity.status(HttpStatus.OK).body("Entidade excluída com sucesso!");
           
    }

}

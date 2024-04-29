package br.com.projeto.labpcp.controller;


import br.com.projeto.labpcp.controller.dto.request.AlterarMateriaRequest;
import br.com.projeto.labpcp.controller.dto.request.InserirMateriaRequest;
import br.com.projeto.labpcp.controller.dto.response.MateriaResponse;
import br.com.projeto.labpcp.datasource.entity.MateriaEntity;
import br.com.projeto.labpcp.service.MateriaService;
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
@RequestMapping("materias")
public class MateriaController {

    private final MateriaService materiaService;

    @PostMapping
    public ResponseEntity<MateriaResponse> salvarMateria(@RequestBody InserirMateriaRequest inserirMateriaRequest, @RequestHeader(name = "Authorization") String token) {

        MateriaResponse novaMateria = materiaService.salvar(inserirMateriaRequest, token.substring(7));
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMateria);

    }

    @GetMapping
    public ResponseEntity<List<MateriaEntity>> buscarMaterias(@RequestHeader(name = "Authorization") String token) {

        List<MateriaEntity> materias = materiaService.buscarTodos(token.substring(7));
        return ResponseEntity.ok(materias);
    }

    @GetMapping("{id}")
    public ResponseEntity<MateriaEntity> buscarMateriaPorId(@PathVariable Long id, @RequestHeader(name = "Authorization") String token) {

        MateriaEntity materiaEntity = materiaService.buscarPorId(id, token.substring(7));
        return ResponseEntity.status(HttpStatus.OK).body(materiaEntity);

    }

    @PutMapping("{id}")
    public ResponseEntity<MateriaEntity> alterarMateria(@PathVariable Long id, @RequestBody AlterarMateriaRequest alterarMateriaRequest, @RequestHeader(name = "Authorization") String token) {


        MateriaEntity materiaAtualizada = materiaService.alterar(id, alterarMateriaRequest, token.substring(7));
        return ResponseEntity.ok(materiaAtualizada);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> excluirMateria(@PathVariable Long id, @RequestHeader(name = "Authorization") String token) {

        materiaService.excluirPorId(id, token.substring(7));
        return ResponseEntity.status(HttpStatus.OK).body("Entidade excluída com sucesso!");
    }


}

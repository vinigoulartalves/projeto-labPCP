package br.com.projeto.labpcp.controller;

import br.com.projeto.labpcp.controller.dto.request.InserirDocenteRequest;
import br.com.projeto.labpcp.controller.dto.response.DocenteResponse;
import br.com.projeto.labpcp.service.DocenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("docentes")
public class DocenteController {

    private final DocenteService docenteService;

    @PostMapping
    public ResponseEntity<DocenteResponse> salvarDocente(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody InserirDocenteRequest inserirDocenteRequest) {

        return ResponseEntity.ok(docenteService.salvar(inserirDocenteRequest, token.substring(7)));

    }

}

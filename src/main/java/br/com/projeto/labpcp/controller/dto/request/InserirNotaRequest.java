package br.com.projeto.labpcp.controller.dto.request;

import java.math.BigDecimal;
import java.util.Date;

public record InserirNotaRequest(Long id, Long alunoId, Long docenteId, Long materiaId, BigDecimal nota,
                                 Date dataNota) {
}

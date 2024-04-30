package br.com.projeto.labpcp.controller.dto.response;

import java.math.BigDecimal;
import java.util.Date;

public record NotaResponse(Long id,
                           Long alunoId,
                           Long docenteId,
                           Long materiaId,
                           BigDecimal nota,
                           Date dataNota) {
}

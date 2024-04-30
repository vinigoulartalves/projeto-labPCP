package br.com.projeto.labpcp.controller.dto.request;

import java.math.BigDecimal;
import java.util.Date;

public record InserirNotaRequest(Long id, Long idAluno, Long idDocente, Long idMateria, BigDecimal nota,
                                 Date dataNota) {
}

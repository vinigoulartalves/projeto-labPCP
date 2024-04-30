package br.com.projeto.labpcp.controller.dto.request;

import java.math.BigDecimal;
import java.util.Date;

public record AlterarNotaRequest(BigDecimal nota, Date dataNota) {
}

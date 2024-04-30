package br.com.projeto.labpcp.controller.dto.response;

import br.com.projeto.labpcp.datasource.entity.TurmaEntity;

import java.util.Date;
import java.util.List;

public record AlunoResponse(Long id,
                            String nome,
                            Date dataNascimento,
                            Long idUsuario,
                            List<TurmaEntity> turmasPresentes) {
}

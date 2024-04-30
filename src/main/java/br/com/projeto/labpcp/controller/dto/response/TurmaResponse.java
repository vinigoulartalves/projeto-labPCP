package br.com.projeto.labpcp.controller.dto.response;

import br.com.projeto.labpcp.datasource.entity.AlunoEntity;

import java.util.List;

public record TurmaResponse(Long id,
                            String nome,
                            Long idDocente,
                            List<AlunoEntity> alunosMatriculados,
                            Long idCurso) {
}

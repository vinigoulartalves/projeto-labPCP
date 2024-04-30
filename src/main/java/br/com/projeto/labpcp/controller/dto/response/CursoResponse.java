package br.com.projeto.labpcp.controller.dto.response;

import br.com.projeto.labpcp.datasource.entity.MateriaEntity;
import br.com.projeto.labpcp.datasource.entity.TurmaEntity;

import java.util.List;

public record CursoResponse(Long id, String nome, List<TurmaEntity> turmasCadastradas,
                            List<MateriaEntity> materiasCadastradas) {
}

package br.com.projeto.labpcp.datasource.repository;

import br.com.projeto.labpcp.datasource.entity.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<CursoEntity, Long> {
}

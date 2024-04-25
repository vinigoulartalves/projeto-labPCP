package br.com.projeto.labpcp.datasource.repository;

import br.com.projeto.labpcp.datasource.entity.TurmaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository extends JpaRepository<TurmaEntity, long> {
}

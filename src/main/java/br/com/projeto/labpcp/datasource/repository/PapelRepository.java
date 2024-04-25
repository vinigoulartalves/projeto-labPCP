package br.com.projeto.labpcp.datasource.repository;

import br.com.projeto.labpcp.datasource.entity.PapelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PapelRepository extends JpaRepository<PapelEntity, Long> {
}

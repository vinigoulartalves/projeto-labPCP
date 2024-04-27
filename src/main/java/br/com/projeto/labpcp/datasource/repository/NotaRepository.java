package br.com.projeto.labpcp.datasource.repository;

import br.com.projeto.labpcp.datasource.entity.NotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository<NotaEntity, Long> {
}

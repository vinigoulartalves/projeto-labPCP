package br.com.projeto.labpcp.datasource.repository;

import br.com.projeto.labpcp.datasource.entity.DocenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocenteRepository extends JpaRepository<DocenteEntity, Long> {
}

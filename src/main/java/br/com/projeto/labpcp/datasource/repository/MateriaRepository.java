package br.com.projeto.labpcp.datasource.repository;

import br.com.projeto.labpcp.datasource.entity.MateriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaRepository extends JpaRepository<MateriaEntity, Long> {
}

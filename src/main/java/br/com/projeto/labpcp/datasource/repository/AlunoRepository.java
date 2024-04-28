package br.com.projeto.labpcp.datasource.repository;

import br.com.projeto.labpcp.datasource.entity.AlunoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoEntity, Long> {


}

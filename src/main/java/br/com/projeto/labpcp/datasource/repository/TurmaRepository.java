package br.com.projeto.labpcp.datasource.repository;

import br.com.projeto.labpcp.datasource.entity.TurmaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface TurmaRepository extends JpaRepository<TurmaEntity, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE TurmaEntity e SET e.nome = COALESCE(:nome, e.nome) WHERE e.id = :id")
    void updateTurmaParcial(Long id, String nome);


}

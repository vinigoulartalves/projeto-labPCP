package br.com.projeto.labpcp.datasource.repository;

import br.com.projeto.labpcp.datasource.entity.CursoEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CursoRepository extends JpaRepository<CursoEntity, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE CursoEntity e SET e.nome = COALESCE(:nome, e.nome) WHERE e.id = :id")
    void updateCursoParcial(Long id, String nome);

}

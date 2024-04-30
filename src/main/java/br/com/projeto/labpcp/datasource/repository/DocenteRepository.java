package br.com.projeto.labpcp.datasource.repository;

import br.com.projeto.labpcp.datasource.entity.DocenteEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface DocenteRepository extends JpaRepository<DocenteEntity, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE DocenteEntity e SET e.nome = COALESCE(:nome, e.nome) WHERE e.id = :id")
    void updateDocenteParcial(Long id, String nome);

}

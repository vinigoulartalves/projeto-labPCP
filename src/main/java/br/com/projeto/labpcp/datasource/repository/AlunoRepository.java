package br.com.projeto.labpcp.datasource.repository;

import br.com.projeto.labpcp.datasource.entity.AlunoEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoEntity, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE AlunoEntity e SET e.nome = COALESCE(:nome, e.nome), " +
            "e.dataNascimento = COALESCE(:dataNascimento, e.dataNascimento) " +
            "WHERE e.id = :id")
    void updateAlunoParcial(Long id, String nome, Date dataNascimento);
}

package br.com.projeto.labpcp.datasource.repository;

import br.com.projeto.labpcp.datasource.entity.NotaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Date;

public interface NotaRepository extends JpaRepository<NotaEntity, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE NotaEntity e SET e.nota = COALESCE(:nota, e.nota), " +
            "e.data = COALESCE(:dataNota, e.data) " +
            "WHERE e.id = :id")
    void updateParcialNota(Long id, BigDecimal nota, Date dataNota);

}

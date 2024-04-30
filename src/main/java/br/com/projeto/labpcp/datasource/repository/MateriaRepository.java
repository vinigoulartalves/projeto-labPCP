package br.com.projeto.labpcp.datasource.repository;

import br.com.projeto.labpcp.datasource.entity.MateriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MateriaRepository extends JpaRepository<MateriaEntity, Long> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE MateriaEntity e SET e.nome = COALESCE(:nome, e.nome) " +
            "WHERE e.id = :id")
    void updateMateriaParcial(@Param("id") Long id, @Param("nome") String nome);


}

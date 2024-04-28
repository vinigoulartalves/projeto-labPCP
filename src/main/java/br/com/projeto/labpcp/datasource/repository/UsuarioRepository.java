package br.com.projeto.labpcp.datasource.repository;

import br.com.projeto.labpcp.datasource.entity.PapelEntity;
import br.com.projeto.labpcp.datasource.entity.UsuarioEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByLogin(String nomeUsuario);


}

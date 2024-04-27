package br.com.projeto.labpcp.datasource.repository;

import br.com.projeto.labpcp.datasource.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByLogin(String nomeUsuario);
}

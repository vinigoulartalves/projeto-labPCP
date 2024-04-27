package br.com.projeto.labpcp.datasource.repository;

import br.com.projeto.labpcp.datasource.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}

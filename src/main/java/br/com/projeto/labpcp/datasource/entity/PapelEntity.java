package br.com.projeto.labpcp.datasource.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@Entity
@Table(name = "papel")
public class PapelEntity implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 50, unique = true)
    private String nome;

    @Override
    public String getAuthority() {
        return this.nome;
    }
}

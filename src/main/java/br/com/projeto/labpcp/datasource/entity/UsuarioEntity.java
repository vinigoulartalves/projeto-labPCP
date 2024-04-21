package br.com.projeto.labpcp.datasource.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "usuario")
public class UsuarioEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", nullable = false, length = 50, unique = true)
    private String login;

    @Column(name = "senha", nullable = false)
    @JsonIgnore
    private String senha;

    @ManyToOne
    @JoinColumn(name = "id_papel")
    private PapelEntity papelEntity;
}

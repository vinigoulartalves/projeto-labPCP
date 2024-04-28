package br.com.projeto.labpcp.datasource.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "aluno")
@Data
public class AlunoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(nullable = false, name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;

    @OneToMany
    @JoinTable(
            name = "aluno_turma",
            joinColumns = @JoinColumn(name = "id_aluno"),
            inverseJoinColumns = @JoinColumn(name = "id_turma")
    )
    private List<TurmaEntity> turmas;
}

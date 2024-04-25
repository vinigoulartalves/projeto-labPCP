package br.com.projeto.labpcp.datasource.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "turma")
@Data
public class TurmaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nome;

    @OneToOne
    @JoinColumn(name = "id_docente")
    private DocenteEntity docente;

    @OneToMany//(mappedBy = "turmas")
    private List<AlunoEntity> alunosMatriculados;

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private CursoEntity curso;

}

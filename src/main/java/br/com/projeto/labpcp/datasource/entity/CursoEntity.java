package br.com.projeto.labpcp.datasource.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "curso")
public class CursoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @OneToMany//(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<TurmaEntity> turmas;

    @OneToMany//(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<MateriaEntity> materias;

}

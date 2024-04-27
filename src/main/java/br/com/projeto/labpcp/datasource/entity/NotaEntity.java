package br.com.projeto.labpcp.datasource.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "nota")
public class NotaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private AlunoEntity aluno;

    @ManyToOne
    @JoinColumn(name = "id_docente")
    private DocenteEntity docente;

    @ManyToOne
    @JoinColumn(name = "id_materia")
    private MateriaEntity materia;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;
}

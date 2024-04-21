package br.com.projeto.labpcp.datasource.entity;

import br.com.projeto.labpcp.utils.PapelEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "papel")
public class PapelEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    public static String enumParaNome(PapelEnum papel) {
        return papel.getDescricao();
    }
}

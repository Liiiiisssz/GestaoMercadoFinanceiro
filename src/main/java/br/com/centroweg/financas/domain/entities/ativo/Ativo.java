package br.com.centroweg.financas.domain.entities.ativo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ativo")
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public abstract class Ativo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ticker;

    private String nome;

    private Double valorAtual;

    public abstract double calcularRisco();
}

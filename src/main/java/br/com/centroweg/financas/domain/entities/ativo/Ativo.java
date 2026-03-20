package br.com.centroweg.financas.domain.entities.ativo;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

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

    private BigDecimal valorAtual;

    public abstract String getInformacaoAdicional();
    public abstract boolean restrito();
    public abstract Double calcularRisco();

    public Ativo(String ticker, String nome, BigDecimal valorAtual) {
        this.ticker = ticker;
        this.nome = nome;
        this.valorAtual = valorAtual;
    }
}

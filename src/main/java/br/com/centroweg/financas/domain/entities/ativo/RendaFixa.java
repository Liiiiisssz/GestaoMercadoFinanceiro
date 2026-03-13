package br.com.centroweg.financas.domain.entities.ativo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "renda_fixo")
@NoArgsConstructor @Getter @Setter
public class RendaFixa extends Ativo{

    private LocalDate dataVencimento;

    private Double taxaContratada;

    private String indexador;

    public RendaFixa(Long id, String ticker, String nome, Double valorAtual, LocalDate dataVencimento, String indexador) {
        super(id, ticker, nome, valorAtual);
        this.dataVencimento = dataVencimento;
        this.indexador = indexador;
    }

    public RendaFixa(LocalDate dataVencimento, Double taxaContratada, String indexador) {
        this.dataVencimento = dataVencimento;
        this.taxaContratada = taxaContratada;
        this.indexador = indexador;
    }

    @Override
    public double calcularRisco() {
        return dataVencimento.isAfter(LocalDate.now().plusYears(5)) ? 2.0 : 1.0;
    }
}

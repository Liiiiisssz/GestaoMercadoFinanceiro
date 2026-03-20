package br.com.centroweg.financas.domain.entities.ativo;

import br.com.centroweg.financas.service.dto.ativo.AtivoRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "renda_fixa")
@NoArgsConstructor @Getter @Setter
public class RendaFixa extends Ativo{

    private LocalDate dataVencimento;

    private Double taxaContratada;

    private String indexador;

    public RendaFixa(Long id, String ticker, String nome, BigDecimal valorAtual, LocalDate dataVencimento, String indexador, Double taxaContratada) {
        super(id, ticker, nome, valorAtual);
        this.dataVencimento = dataVencimento;
        this.indexador = indexador;
        this.taxaContratada = taxaContratada;
    }

    public RendaFixa(LocalDate dataVencimento, Double taxaContratada, String indexador) {
        this.dataVencimento = dataVencimento;
        this.taxaContratada = taxaContratada;
        this.indexador = indexador;
    }

    @Override
    public void configurar(AtivoRequestDTO dto){
        super.configurar(dto);
        this.dataVencimento = dto.dataVencimento();
    }

    @Override
    public String getInformacaoAdicional() {
        return this.indexador;
    }

    @Override
    public boolean restrito() {
        return false;
    }

    @Override
    public Double calcularRisco() {
        return (dataVencimento != null && dataVencimento.isAfter(LocalDate.now())) ? 1.0 : 5.0;
    }
}

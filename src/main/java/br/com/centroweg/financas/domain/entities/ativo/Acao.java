package br.com.centroweg.financas.domain.entities.ativo;

import br.com.centroweg.financas.service.dto.ativo.AtivoRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "acao")
@NoArgsConstructor @Getter @Setter
public class Acao extends Ativo{

    private String setor;

    public Acao(String ticker, String nome, BigDecimal valor, String setor){
        super(ticker, nome, valor);
        this.setor = setor;
    }
    public Acao(Long id, String ticker, String nome, BigDecimal valor, String setor) {
        super(id, ticker, nome, valor);
        this.setor = setor;
    }

    @Override
    public String getInformacaoAdicional() {
        return this.setor;
    }

    @Override
    public boolean restrito() {
        return true;
    }

    @Override
    public Double calcularRisco() {
        return 5.0;
    }
}

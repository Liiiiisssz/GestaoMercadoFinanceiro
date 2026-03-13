package br.com.centroweg.financas.domain.entities.ativo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "acao")
@NoArgsConstructor @Getter @Setter
public class Acao extends Ativo{

    private String setor;

    public Acao(String ticker, String nome, double valor, String setor){
        super(null, ticker, nome, valor);
        this.setor = setor;
    }

    @Override
    public double calcularRisco() {
        return 5.0;
    }
}

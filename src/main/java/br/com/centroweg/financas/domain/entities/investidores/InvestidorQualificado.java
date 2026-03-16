package br.com.centroweg.financas.domain.entities.investidores;

import br.com.centroweg.financas.domain.entities.ativo.Ativo;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("QUALIFICADO")
public class InvestidorQualificado extends Investidor {
    @Override
    public boolean podeInvestir(Ativo ativo) {
        return true;
    }
}

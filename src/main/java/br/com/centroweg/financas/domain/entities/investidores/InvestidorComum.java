package br.com.centroweg.financas.domain.entities.investidores;

import br.com.centroweg.financas.domain.entities.ativo.Acao;
import br.com.centroweg.financas.domain.entities.ativo.Ativo;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("COMUM")
public class InvestidorComum extends Investidor{
    @Override
    public boolean podeInvestir(Ativo ativo) {
        return !(ativo instanceof Acao);
    }
}

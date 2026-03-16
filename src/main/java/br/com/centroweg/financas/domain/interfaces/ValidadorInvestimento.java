package br.com.centroweg.financas.domain.interfaces;

import br.com.centroweg.financas.domain.entities.ativo.Ativo;
import br.com.centroweg.financas.domain.entities.investidores.Investidor;

public interface ValidadorInvestimento {
    boolean SeAplica(Ativo ativo);
    void validar(Investidor investidor, Ativo ativo);
}

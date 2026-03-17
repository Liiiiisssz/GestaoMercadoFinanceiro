package br.com.centroweg.financas.domain.interfaces;

import br.com.centroweg.financas.domain.entities.ativo.Ativo;

public interface CalculoImpostoStrategy {
    Double calcular(Double valor);
    boolean isAplicavel(Ativo ativo);
}

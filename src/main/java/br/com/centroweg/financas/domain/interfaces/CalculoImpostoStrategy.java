package br.com.centroweg.financas.domain.interfaces;

import br.com.centroweg.financas.domain.entities.ativo.Ativo;

import java.math.BigDecimal;

public interface CalculoImpostoStrategy {
    BigDecimal calcular(BigDecimal valor);
    boolean isAplicavel(Ativo ativo);
}

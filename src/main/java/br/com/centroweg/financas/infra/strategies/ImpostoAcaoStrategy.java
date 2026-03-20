package br.com.centroweg.financas.infra.strategies;

import br.com.centroweg.financas.domain.entities.ativo.Acao;
import br.com.centroweg.financas.domain.entities.ativo.Ativo;
import br.com.centroweg.financas.domain.interfaces.CalculoImpostoStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ImpostoAcaoStrategy implements CalculoImpostoStrategy {
    @Override
    public BigDecimal calcular(BigDecimal valor) {
        return valor.multiply(new BigDecimal("0.15"));
    }

    @Override
    public boolean isAplicavel(Ativo ativo) {
        return ativo instanceof Acao;
    }
}

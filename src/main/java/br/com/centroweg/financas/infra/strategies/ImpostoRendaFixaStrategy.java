package br.com.centroweg.financas.infra.strategies;

import br.com.centroweg.financas.domain.entities.ativo.Ativo;
import br.com.centroweg.financas.domain.entities.ativo.RendaFixa;
import br.com.centroweg.financas.domain.interfaces.CalculoImpostoStrategy;
import org.springframework.stereotype.Component;

@Component
public class ImpostoRendaFixaStrategy implements CalculoImpostoStrategy {

    @Override
    public Double calcular(Double valor) {
        return valor * 0.05;
    }

    @Override
    public boolean isAplicavel(Ativo ativo) {
        return ativo instanceof RendaFixa;
    }
}

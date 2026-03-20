package br.com.centroweg.financas.service.usecases.ativo;

import br.com.centroweg.financas.domain.entities.ativo.Ativo;
import br.com.centroweg.financas.domain.interfaces.CalculoImpostoStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImpostoResolverService {

    private final List<CalculoImpostoStrategy> strategies;

    public BigDecimal calcularImpostoInterno(Ativo ativo, BigDecimal valorInvestido){
        return strategies.stream()
                .filter(strategy -> strategy.isAplicavel(ativo))
                .findFirst()
                .map(strategy -> strategy.calcular(valorInvestido))
                .orElse(BigDecimal.ZERO);
    }
}

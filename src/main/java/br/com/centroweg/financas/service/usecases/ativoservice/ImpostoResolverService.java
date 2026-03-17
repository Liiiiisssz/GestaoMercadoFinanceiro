package br.com.centroweg.financas.service.usecases.ativoservice;

import br.com.centroweg.financas.domain.entities.ativo.Ativo;
import br.com.centroweg.financas.domain.interfaces.CalculoImpostoStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImpostoResolverService {


    private final List<CalculoImpostoStrategy> strategies;

    public Double calcularImpostoInterno(Ativo ativo, Double valorInvestido){
        return strategies.stream()
                .filter(strategy -> strategy.isAplicavel(ativo))
                .findFirst()
                .map(strategy -> strategy.calcular(valorInvestido))
                .orElse(0.0);
    }


}

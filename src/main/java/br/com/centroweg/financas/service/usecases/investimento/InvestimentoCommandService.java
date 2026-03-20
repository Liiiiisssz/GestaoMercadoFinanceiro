package br.com.centroweg.financas.service.usecases.investimento;

import br.com.centroweg.financas.domain.entities.investidores.Investidor;
import br.com.centroweg.financas.domain.entities.investimento.OrdemInvestimento;
import br.com.centroweg.financas.infra.repository.investidores.InvestidorRepository;
import br.com.centroweg.financas.infra.repository.investimento.OrdemInvestimentoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvestimentoCommandService {

    private final OrdemInvestimentoRepository ordemRepository;
    private final InvestidorRepository investidorRepository;

@Transactional
    public OrdemInvestimento salvarOrdem(OrdemInvestimento investimento){
    return ordemRepository.save(investimento);
}
    @Transactional
    public void atualizarSaldo(Investidor investidor) {
        investidorRepository.save(investidor);
    }
}

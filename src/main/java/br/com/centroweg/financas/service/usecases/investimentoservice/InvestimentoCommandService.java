package br.com.centroweg.financas.service.usecases.investimentoservice;

import br.com.centroweg.financas.domain.entities.investidores.Investidor;
import br.com.centroweg.financas.domain.entities.investimento.OrdemInvestimento;
import br.com.centroweg.financas.infra.repository.investidores.InvestidorRepository;
import br.com.centroweg.financas.infra.repository.investimento.OrdemInvestimentoRepository;
import br.com.centroweg.financas.service.dto.investidor.InvestidorRequestDTO;
import br.com.centroweg.financas.service.dto.investidor.InvestidorResponseDTO;
import br.com.centroweg.financas.service.mapper.InvestidorMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvestimentoCommandService {

    private final InvestidorRepository repository;
    private final InvestidorMapper mapper;
    private final OrdemInvestimentoRepository ordemInvestimentoRepository;


@Transactional
public InvestidorResponseDTO SaveNewInvestidor(Investidor investidor){
    return mapper.toDTO(repository.save(investidor));
}

    @Transactional
    public void atualizarSaldo(Investidor investidor) {
        repository.save(investidor);
    }

    @Transactional
    public OrdemInvestimento salvarOrdem(OrdemInvestimento ordem) {
        return ordemInvestimentoRepository.save(ordem);
    }
    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Investidor não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }

}

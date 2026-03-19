package br.com.centroweg.financas.service.usecases.investimentoservice;

import br.com.centroweg.financas.domain.entities.investidores.Investidor;
import br.com.centroweg.financas.infra.repository.investidores.InvestidorRepository;
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


@Transactional
public InvestidorResponseDTO SaveNewInvestidor(InvestidorRequestDTO request){
    Investidor investidor = mapper.toEntity(request);
    return mapper.toDTO(repository.save(investidor));
}

@Transactional
    public InvestidorResponseDTO PutBalance(Long id, Double novoSaldo){
    Investidor investidor = repository.findById(id).orElseThrow( () -> new EntityNotFoundException("Investidor nao encontrado"));

    investidor.setSaldo(novoSaldo);
    return mapper.toDTO(repository.save(investidor));
}
    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Investidor não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }

}

package br.com.centroweg.financas.service.usecases.investidorservice;

import br.com.centroweg.financas.domain.entities.investidores.Investidor;
import br.com.centroweg.financas.infra.repository.investidores.InvestidorRepository;
import br.com.centroweg.financas.service.dto.investidor.InvestidorRequestDTO;
import br.com.centroweg.financas.service.dto.investidor.InvestidorResponseDTO;
import br.com.centroweg.financas.service.mapper.InvestidorMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InvestidorCommandService {

    private final InvestidorRepository repository;
    private final InvestidorMapper mapper;

    @Transactional
    public InvestidorResponseDTO salvar(InvestidorRequestDTO dto) {
        Investidor investidor = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(investidor));
    }

    @Transactional
    public InvestidorResponseDTO atualizarNome(Long id, String novoNome) {
        Investidor investidor = buscarOuLancar(id);
        investidor.setNome(novoNome);
        return mapper.toDTO(repository.save(investidor));
    }

    @Transactional
    public InvestidorResponseDTO atualizarSaldo(Long id, Double novoSaldo) {
        Investidor investidor = buscarOuLancar(id);
        investidor.setSaldo(novoSaldo);
        return mapper.toDTO(repository.save(investidor));
    }

    @Transactional
    public void persistirSaldoAtualizado(Investidor investidor) {
        repository.save(investidor);
    }

    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Investidor não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }

    private Investidor buscarOuLancar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Investidor não encontrado com ID: " + id));
    }
}
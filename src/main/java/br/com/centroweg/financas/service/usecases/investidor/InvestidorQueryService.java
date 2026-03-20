package br.com.centroweg.financas.service.usecases.investidor;

import br.com.centroweg.financas.domain.entities.investidores.Investidor;
import br.com.centroweg.financas.infra.repository.investidores.InvestidorRepository;
import br.com.centroweg.financas.service.dto.investidor.InvestidorResponseDTO;
import br.com.centroweg.financas.service.mapper.InvestidorMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InvestidorQueryService {

    private final InvestidorRepository repository;
    private final InvestidorMapper mapper;

    public List<InvestidorResponseDTO> listarTodos() {
        return mapper.toDTOList(repository.findAll());
    }

    public InvestidorResponseDTO buscarPorId(Long id) {
        return mapper.toDTO(buscarEntidadePorId(id));
    }

    public List<InvestidorResponseDTO> buscarPorNome(String nome) {
        return mapper.toDTOList(repository.findByNomeContainingIgnoreCase(nome));
    }

    public List<InvestidorResponseDTO> buscarPorSaldoMinimo(BigDecimal saldo) {
        return mapper.toDTOList(repository.findBySaldoGreaterThanEqual(saldo));
    }

    public Investidor buscarEntidadePorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Investidor não encontrado com ID: " + id));
    }
}
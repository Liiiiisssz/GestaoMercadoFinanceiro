package br.com.centroweg.financas.service.usecases.ativoservice;

import br.com.centroweg.financas.domain.entities.ativo.Ativo;
import br.com.centroweg.financas.infra.repository.ativo.AtivoRepository;
import br.com.centroweg.financas.service.dto.ativo.AtivoResponseDTO;
import br.com.centroweg.financas.service.mapper.AtivoMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AtivoQueryService {

    private final AtivoRepository repository;
    private final AtivoMapper mapper;
    private final ImpostoResolverService impostoResolver;

    public List<AtivoResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(ativo -> mapper.toDTO(ativo, BigDecimal.ZERO))
                .toList();
    }

    public AtivoResponseDTO buscarPorTicker(String ticker, BigDecimal valorSimulado) {
        Ativo ativo = buscarEntidadePorTicker(ticker);
        BigDecimal imposto = impostoResolver.calcularImpostoInterno(ativo, valorSimulado);
        return mapper.toDTO(ativo, imposto);
    }

    public Ativo buscarEntidadePorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ativo não encontrado com ID: " + id));
    }

    private Ativo buscarEntidadePorTicker(String ticker) {
        return repository.findByTicker(ticker)
                .orElseThrow(() -> new EntityNotFoundException("Ativo não encontrado"));
    }
}

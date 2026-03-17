package br.com.centroweg.financas.service.usecases;

import br.com.centroweg.financas.domain.entities.ativo.Ativo;
import br.com.centroweg.financas.domain.interfaces.CalculoImpostoStrategy;
import br.com.centroweg.financas.infra.repository.ativo.AtivoRepository;
import br.com.centroweg.financas.service.dto.ativo.AtivoResponseDTO;
import br.com.centroweg.financas.service.mapper.AtivoMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AtivoService {

    private final AtivoRepository repository;
    private final AtivoMapper mapper;

    private final List<CalculoImpostoStrategy> strategyImposto;

    @Transactional(readOnly = true)
    public Double calcularImpostoInterno(Ativo ativo, Double valorInvestido){
        return strategyImposto.stream()
                .filter(strategy -> strategy.isAplicavel(ativo))
                .findFirst()
                .map(strategy -> strategy.calcular(valorInvestido))
                .orElse(0.0);
    }

    @Transactional(readOnly = true)
    public List<AtivoResponseDTO> listarTodos() {
        List<Ativo> ativos = repository.findAll();
        return ativos.stream()
                .map(ativo -> mapper.toDTO(ativo, 0.0))
                .toList();
    }

    @Transactional(readOnly = true)
    public AtivoResponseDTO buscarPorTicker(String ticker, Double valorSimulado){
        Ativo ativo = repository.findByTicker(ticker)
                .orElseThrow(() -> new EntityNotFoundException("Ativo não encontrado"));

        Double imposto = calcularImpostoInterno(ativo, valorSimulado);

        return mapper.toDTO(ativo, imposto);
    }

    @Transactional
    public AtivoResponseDTO salvar(Ativo ativo) {
        Ativo salvo = repository.save(ativo);
        return mapper.toDTO(salvo, 0.0);
    }

    public Ativo buscarEntidadePorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ativo não encontrado com ID: " + id));
    }
}

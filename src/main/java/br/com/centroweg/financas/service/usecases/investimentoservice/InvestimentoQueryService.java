package br.com.centroweg.financas.service.usecases.investimentoservice;

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
public class InvestimentoQueryService {

    private final InvestidorMapper mapper;
    private final InvestidorRepository repository;

    public List<InvestidorResponseDTO> ListAll(){
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }
    public List<InvestidorResponseDTO> ListByName(String nome){
        return repository.findByNomeContainingIgnoreCase(nome).stream()
                .map(mapper::toDTO).toList();
    }
    public List<InvestidorResponseDTO> buscarPorSaldoMinimo(BigDecimal saldo) {
        return repository.findBySaldoGreaterThanEqual(saldo).stream()
                .map(mapper::toDTO)
                .toList();
    }
    public Investidor buscarEntidadePorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Investidor não encontrado com ID: " + id));
    }
}

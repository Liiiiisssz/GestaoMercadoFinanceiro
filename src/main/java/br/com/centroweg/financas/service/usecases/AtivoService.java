package br.com.centroweg.financas.service.usecases;

import br.com.centroweg.financas.domain.entities.ativo.Ativo;
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
    @Transactional(readOnly = true)
    public List<AtivoResponseDTO> listarTodos() {
        List<Ativo> ativos = repository.findAll();
        return mapper.toDTOList(ativos);
    }

    @Transactional(readOnly = true)
    public AtivoResponseDTO buscarPorTicker(String ticker){
        return repository.findByTicker(ticker).map(mapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Ativo com ticker " + ticker + " não encontrado."));
    }

    @Transactional
    public AtivoResponseDTO salvar(Ativo ativo) {
        Ativo salvo = repository.save(ativo);
        return mapper.toDTO(salvo);
    }
    public Ativo buscarEntidadePorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ativo não encontrado com ID: " + id));
    }



}

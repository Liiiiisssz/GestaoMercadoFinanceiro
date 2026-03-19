package br.com.centroweg.financas.service.usecases.ativoservice;

import br.com.centroweg.financas.domain.entities.ativo.Ativo;
import br.com.centroweg.financas.infra.repository.ativo.AtivoRepository;
import br.com.centroweg.financas.service.dto.ativo.AtivoResponseDTO;
import br.com.centroweg.financas.service.mapper.AtivoMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AtivoCommandService {

        private final AtivoRepository repository;
        private final AtivoMapper mapper;

        @Transactional
        public AtivoResponseDTO save(Ativo ativo){
            Ativo salvo =  repository.save(ativo);
            return mapper.toDTO(salvo, BigDecimal.ZERO);
        }
}

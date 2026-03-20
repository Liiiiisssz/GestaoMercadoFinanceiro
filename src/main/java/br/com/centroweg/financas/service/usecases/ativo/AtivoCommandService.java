package br.com.centroweg.financas.service.usecases.ativo;

import br.com.centroweg.financas.domain.entities.ativo.Ativo;
import br.com.centroweg.financas.infra.repository.ativo.AtivoRepository;
import br.com.centroweg.financas.service.dto.ativo.AtivoRequestDTO;
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
        private final ImpostoResolverService impostoResolver;

        @Transactional
        public AtivoResponseDTO save(AtivoRequestDTO dto){
            Ativo salvo = repository.save(mapper.toEntity(dto));
            BigDecimal imposto = impostoResolver.calcularImpostoInterno(salvo, dto.valorAtual());
            return mapper.toDTO(salvo, imposto);
        }
}

package br.com.centroweg.financas.service.mapper.ativo;

import br.com.centroweg.financas.domain.entities.ativo.Acao;
import br.com.centroweg.financas.domain.entities.ativo.Ativo;
import br.com.centroweg.financas.domain.entities.ativo.RendaFixa;
import br.com.centroweg.financas.domain.interfaces.AtivoMapperStrategy;
import br.com.centroweg.financas.service.dto.ativo.AtivoRequestDTO;
import br.com.centroweg.financas.service.dto.ativo.AtivoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Supplier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AtivoMapper {

    private final List<AtivoMapperStrategy> strategies;

    public Ativo toEntity(AtivoRequestDTO dto) {
        if (dto == null) return null;

        return strategies.stream()
                .filter(strategy -> strategy.supports(dto))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tipo de DTO não suportado"))
                .toEntity(dto);
    }

    public AtivoResponseDTO toDTO(Ativo ativo, BigDecimal impostoCalculado){
        if(ativo == null) return null;

        return strategies.stream()
                .filter(strategy -> strategy.supports(ativo))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tipo de Entidade não suportada"))
                .toDTO(ativo, impostoCalculado);
    }
}

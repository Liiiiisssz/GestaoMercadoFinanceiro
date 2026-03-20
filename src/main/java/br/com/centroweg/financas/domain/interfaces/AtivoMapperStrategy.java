package br.com.centroweg.financas.domain.interfaces;

import br.com.centroweg.financas.domain.entities.ativo.Ativo;
import br.com.centroweg.financas.service.dto.ativo.AtivoRequestDTO;
import br.com.centroweg.financas.service.dto.ativo.AtivoResponseDTO;

import java.math.BigDecimal;

public interface AtivoMapperStrategy {
    boolean supports(AtivoRequestDTO dto);
    boolean supports(Ativo entity);

    Ativo toEntity(AtivoRequestDTO dto);
    AtivoResponseDTO toDTO(Ativo entity, BigDecimal impostoCalculado);
}

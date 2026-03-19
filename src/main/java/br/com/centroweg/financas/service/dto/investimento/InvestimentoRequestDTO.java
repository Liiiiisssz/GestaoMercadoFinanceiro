package br.com.centroweg.financas.service.dto.investimento;

import java.math.BigDecimal;

public record InvestimentoRequestDTO(
        Long investidorId,
        Long ativoId,
        BigDecimal valor
) {
}

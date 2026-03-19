package br.com.centroweg.financas.service.dto.investidor;

import java.math.BigDecimal;

public record InvestidorResponseDTO(
        Long id,
        String nome,
        String tipo,
        BigDecimal saldo,
        Integer quantidadeAtivos
) {
}

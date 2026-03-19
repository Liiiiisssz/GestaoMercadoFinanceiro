package br.com.centroweg.financas.service.dto.investidor;

import java.math.BigDecimal;

public record InvestidorRequestDTO(
        String nome,
        String cpf,
        BigDecimal saldoInicial,
        String tipo
) {
}

package br.com.centroweg.financas.service.dto.ativo;

import java.math.BigDecimal;

public record AtivoRequestDTO(
        String ticker,
        String nome,
        BigDecimal valorAtual,
        String tipo,
        Double risco,
        String detalhesAtivo
) {
}

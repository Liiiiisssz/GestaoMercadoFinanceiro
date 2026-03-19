package br.com.centroweg.financas.service.dto.ativo;

import java.math.BigDecimal;

public record AtivoResponseDTO(
        Long id,
        String ticker,
        String nome,
        BigDecimal valorAtual,
        String tipo,
        Double risco,
        String detalhesAtivo,
        Double impostoEstimado
) {
}

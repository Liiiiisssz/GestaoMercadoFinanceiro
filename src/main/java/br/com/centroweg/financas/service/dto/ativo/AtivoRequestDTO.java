package br.com.centroweg.financas.service.dto.ativo;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AtivoRequestDTO(
        String ticker,
        String nome,
        BigDecimal valorAtual,
        String tipo,
        Double risco,
        LocalDate dataVencimento
) {
}

package br.com.centroweg.financas.service.dto.ativo;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RendaFixaResponseDTO(
        Long id,
        String ticker,
        String nome,
        BigDecimal valorAtual,
        String tipo,
        Double risco,
        BigDecimal impostoEstimado,
        LocalDate dataVencimento,
        Double taxaContratada,
        String indexador
) implements AtivoResponseDTO {}

package br.com.centroweg.financas.service.dto.ativo;

import java.math.BigDecimal;

public record AcaoResponseDTO(
        Long id,
        String ticker,
        String nome,
        BigDecimal valorAtual,
        String tipo,
        Double risco,
        BigDecimal impostoEstimado,
        String setor
) implements AtivoResponseDTO {}

package br.com.centroweg.financas.service.dto.ativo;

import java.math.BigDecimal;

public record AcaoRequestDTO(
        String ticker,
        String nome,
        BigDecimal valorAtual,
        String setor
) implements AtivoRequestDTO {}

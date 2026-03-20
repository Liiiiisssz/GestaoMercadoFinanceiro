package br.com.centroweg.financas.service.dto.ativo;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RendaFixaRequestDTO(
        String ticker,
        String nome,
        BigDecimal valorAtual,
        LocalDate dataVencimento,
        Double taxaContratada,
        String indexador
) implements AtivoRequestDTO {}

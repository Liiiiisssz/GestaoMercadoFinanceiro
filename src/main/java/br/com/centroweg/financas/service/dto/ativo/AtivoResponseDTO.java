package br.com.centroweg.financas.service.dto.ativo;

import java.math.BigDecimal;

public sealed interface AtivoResponseDTO permits AcaoResponseDTO, RendaFixaResponseDTO{
        Long id();
        String ticker();
        String nome();
        BigDecimal valorAtual();
        String tipo();
        Double risco();
        BigDecimal impostoEstimado();
}

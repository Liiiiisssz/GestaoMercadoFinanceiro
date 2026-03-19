package br.com.centroweg.financas.service.dto.investimento;

import java.math.BigDecimal;

public record InvestimentoResponseDTO (
        String protocolo,
        String investidorNome,
        String ativoTicker,
        BigDecimal valorTotal,
        BigDecimal impostoRetido,
        String dataHora
){
}

package br.com.centroweg.financas.service.dto.investimento;

import java.math.BigDecimal;

public record HistoricoPrecosDTO (
        String ticker,
        BigDecimal preco,
        String dataRegistro
){
}

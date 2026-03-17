package br.com.centroweg.financas.service.dto.investimento;

public record HistoricoPrecosDTO (
        String ticker,
        Double preco,
        String dataRegistro
){
}

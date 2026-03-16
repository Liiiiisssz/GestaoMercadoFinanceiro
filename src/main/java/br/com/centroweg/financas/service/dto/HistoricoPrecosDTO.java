package br.com.centroweg.financas.service.dto;

public record HistoricoPrecosDTO (
        String ticker,
        Double preco,
        String dataRegistro
){
}

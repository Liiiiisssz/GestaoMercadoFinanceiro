package br.com.centroweg.financas.service.dto;

public record InvestimentoResponseDTO (
        String protocolo,
        String investidorNome,
        String ativoTicker,
        Double valorTotal,
        Double impostoRetido,
        String dataHora
){
}

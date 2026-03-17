package br.com.centroweg.financas.service.dto.investimento;

public record InvestimentoResponseDTO (
        String protocolo,
        String investidorNome,
        String ativoTicker,
        Double valorTotal,
        Double impostoRetido,
        String dataHora
){
}

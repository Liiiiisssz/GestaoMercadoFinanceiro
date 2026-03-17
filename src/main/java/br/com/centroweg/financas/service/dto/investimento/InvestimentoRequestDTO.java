package br.com.centroweg.financas.service.dto.investimento;

public record InvestimentoRequestDTO(
        Long investidorId,
        String tickerAtivo,
        Double quantidadde
) {
}

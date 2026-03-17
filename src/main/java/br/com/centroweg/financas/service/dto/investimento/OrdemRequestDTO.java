package br.com.centroweg.financas.service.dto.investimento;

public record OrdemRequestDTO(
        Long investidorId,
        String tickerAtivo,
        Double quantidade,
        String tipoOperacao
) {
}

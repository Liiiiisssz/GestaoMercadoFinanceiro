package br.com.centroweg.financas.service.dto;

public record OrdemRequestDTO(
        Long investidorId,
        String tickerAtivo,
        Double quantidade,
        String tipoOperacao
) {
}

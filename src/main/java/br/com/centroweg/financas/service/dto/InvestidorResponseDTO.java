package br.com.centroweg.financas.service.dto;

public record InvestidorResponseDTO(
        Long id,
        String nome,
        String tipo,
        Double saldo,
        Integer quantidadeAtivos
) {
}

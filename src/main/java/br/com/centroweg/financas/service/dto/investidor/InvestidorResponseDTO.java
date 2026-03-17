package br.com.centroweg.financas.service.dto.investidor;

public record InvestidorResponseDTO(
        Long id,
        String nome,
        String tipo,
        Double saldo,
        Integer quantidadeAtivos
) {
}

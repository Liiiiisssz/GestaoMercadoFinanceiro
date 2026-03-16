package br.com.centroweg.financas.service.dto;

public record InvestidorRequestDTO(
        String nome,
        String cpf,
        Double saldoInicial,
        String tipo
) {
}

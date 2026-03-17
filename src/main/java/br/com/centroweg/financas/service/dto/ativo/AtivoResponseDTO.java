package br.com.centroweg.financas.service.dto.ativo;

public record AtivoResponseDTO(
        Long id,
        String ticker,
        String nome,
        Double valorAtual,
        String tipo,
        Double risco,
        String setor,
        String indexador,
        Double impostoEstimado
) {
}

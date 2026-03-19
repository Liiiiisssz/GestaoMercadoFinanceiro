package br.com.centroweg.financas.service.mapper;

import br.com.centroweg.financas.domain.entities.ativo.Ativo;
import br.com.centroweg.financas.service.dto.ativo.AtivoResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AtivoMapper {

    public AtivoResponseDTO toDTO(Ativo ativo, Double impostoCalculado){
        if(ativo == null) return null;

        return new AtivoResponseDTO(
                ativo.getId(),
                ativo.getTicker(),
                ativo.getNome(),
                ativo.getValorAtual(),
                ativo.getClass().getSimpleName(),
                ativo.calcularRisco(),
                ativo.getInformacaoAdicional(),
                impostoCalculado
        );
    }

    public List<AtivoResponseDTO> toDTOList(List<Ativo> ativos){
        return ativos.stream()
                .map(ativo -> this.toDTO(ativo, 0.0))
                .collect(Collectors.toList());
    }
}

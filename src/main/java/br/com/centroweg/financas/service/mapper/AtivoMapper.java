package br.com.centroweg.financas.service.mapper;

import br.com.centroweg.financas.domain.entities.ativo.Acao;
import br.com.centroweg.financas.domain.entities.ativo.Ativo;
import br.com.centroweg.financas.domain.entities.ativo.RendaFixa;
import br.com.centroweg.financas.service.dto.ativo.AtivoRequestDTO;
import br.com.centroweg.financas.service.dto.ativo.AtivoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Supplier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AtivoMapper {
    private final Map<String, Supplier<? extends Ativo>> tiposAtivo = Map.of(
            "ACAO", Acao::new,
            "RENDA_FIXA", RendaFixa::new
    );
    public Ativo toEntity(AtivoRequestDTO dto) {
        if (dto == null) return null;

        Supplier<? extends Ativo> criador = tiposAtivo.get(dto.tipo().toUpperCase());

        if(criador == null){
            throw new IllegalArgumentException("Tipo de ativo não suportado");
        }
        Ativo ativo = criador.get();

        ativo.setTicker(dto.ticker());
        ativo.setNome(dto.nome());
        ativo.setValorAtual(dto.valorAtual());

        return ativo;
    }

    public AtivoResponseDTO toDTO(Ativo ativo, BigDecimal impostoCalculado){
        if(ativo == null) return null;

        return new AtivoResponseDTO(
                ativo.getId(),
                ativo.getTicker(),
                ativo.getNome(),
                ativo.getValorAtual(),
                ativo.getClass().getSimpleName(),
                ativo.calcularRisco(),
                impostoCalculado
        );
    }

    public List<AtivoResponseDTO> toDTOList(List<Ativo> ativos){
        return ativos.stream()
                .map(ativo -> this.toDTO(ativo, BigDecimal.valueOf(0.0)))
                .collect(Collectors.toList());
    }
}

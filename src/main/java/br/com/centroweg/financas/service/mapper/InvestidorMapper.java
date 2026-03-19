package br.com.centroweg.financas.service.mapper;

import br.com.centroweg.financas.domain.entities.investidores.Investidor;
import br.com.centroweg.financas.domain.entities.investidores.InvestidorComum;
import br.com.centroweg.financas.domain.entities.investidores.InvestidorQualificado;
import br.com.centroweg.financas.service.dto.investidor.InvestidorRequestDTO;
import br.com.centroweg.financas.service.dto.investidor.InvestidorResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Component
public class InvestidorMapper {

    private final Map<String, Supplier<Investidor>> types = Map.of(
            "QUALIFICADO", InvestidorQualificado::new,
            "COMUM", InvestidorComum::new
    );
    public Investidor toEntity(InvestidorRequestDTO dto){
        if(dto == null) return null;

        Investidor investidor = types.getOrDefault(
                dto.tipo().toUpperCase(),
                InvestidorComum::new)
                .get();

        investidor.setNome(dto.nome());
        investidor.setSaldo(dto.saldoInicial());
        return investidor;
    }

    public InvestidorResponseDTO toDTO(Investidor ent){
        if (ent == null) return null;
        return new InvestidorResponseDTO(
                ent.getId(),
                ent.getNome(),
                ent.getClass().getSimpleName().replace("Investidor", ""),
                ent.getSaldo(),
                0
        );
    }

    public List<InvestidorResponseDTO> toDTOList(List<Investidor> investidores){
        return investidores.stream()
                .map(this::toDTO)
                .toList();
    }
}

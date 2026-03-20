package br.com.centroweg.financas.service.dto.ativo;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AcaoRequestDTO.class, name = "ACAO"),
        @JsonSubTypes.Type(value = RendaFixaRequestDTO.class, name = "RENDA_FIXA")
})
public interface AtivoRequestDTO{
    String ticker();
    String nome();
    BigDecimal valorAtual();
}

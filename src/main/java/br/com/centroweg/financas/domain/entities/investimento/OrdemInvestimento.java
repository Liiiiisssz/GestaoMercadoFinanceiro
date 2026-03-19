package br.com.centroweg.financas.domain.entities.investimento;

import br.com.centroweg.financas.domain.entities.ativo.Ativo;
import br.com.centroweg.financas.domain.entities.investidores.Investidor;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ordem_investimento")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class OrdemInvestimento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Investidor investidor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ativo ativo;

    private Double quantidade;

    private BigDecimal precoExecucao;

    @Enumerated(EnumType.STRING)
    private TipoOperacao tipo;

    private BigDecimal imposto;

    private LocalDateTime dataHora;
}
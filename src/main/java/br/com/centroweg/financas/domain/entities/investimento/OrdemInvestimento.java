package br.com.centroweg.financas.domain.entities.investimento;

import br.com.centroweg.financas.domain.entities.ativo.Ativo;
import br.com.centroweg.financas.domain.entities.investidores.Investidor;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ordem_investimento")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrdemInvestimento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Investidor investidor;

    @ManyToOne
    private Ativo ativo;

    private Double quantidade;

    private Double precoExecucao;

    @Enumerated(EnumType.STRING)
    private TipoOperacao tipo;

    private LocalDateTime dataHora;
}

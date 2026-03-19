package br.com.centroweg.financas.domain.entities.investimento;

import br.com.centroweg.financas.domain.entities.ativo.Ativo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "historico_precos")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class HistoricoPrecos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ativo_id")
    private Ativo ativo;

    private BigDecimal preco;

    private LocalDateTime dataRegistro;
}

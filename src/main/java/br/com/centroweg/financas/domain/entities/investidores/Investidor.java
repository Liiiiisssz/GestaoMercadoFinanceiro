package br.com.centroweg.financas.domain.entities.investidores;

import br.com.centroweg.financas.domain.entities.ativo.Ativo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "investidor")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_investidor")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public abstract class Investidor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private Double saldo;

    public abstract boolean podeInvestir(Ativo ativo);
}

package com.mvbackend.domain.model;

import com.mvbackend.domain.dto.DadosCadastroServico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private Double preco;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    public Servico( DadosCadastroServico dadosCadastroServico, Cliente cliente, Veiculo veiculo ) {
        this.descricao = dadosCadastroServico.descricao();
        this.preco = dadosCadastroServico.preco();
        this.cliente = cliente;
        this.veiculo = veiculo;
    }
}

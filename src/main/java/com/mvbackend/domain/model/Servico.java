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

    private Double preco;


    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;


    public Servico( DadosCadastroServico dadosCadastroServico) {
        this.descricao = dadosCadastroServico.descricao();
        this.preco = dadosCadastroServico.preco();
    }
}

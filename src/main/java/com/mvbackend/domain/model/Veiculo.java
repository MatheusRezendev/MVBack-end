package com.mvbackend.domain.model;

import com.mvbackend.domain.dto.DadosCadastroVeiculo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;
    private String modelo;
    private Integer ano;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL)
    private List<Agendamento> agendamentos;

    public Veiculo( DadosCadastroVeiculo dadosCadastroVeiculo, Cliente cliente ){
        this.marca = dadosCadastroVeiculo.marca();
        this.modelo = dadosCadastroVeiculo.modelo();
        this.ano = dadosCadastroVeiculo.ano();
        this.cliente = cliente;
        this.agendamentos = new ArrayList<>();
    }
}

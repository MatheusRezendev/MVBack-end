package com.mvbackend.domain.model;

import com.mvbackend.domain.dto.DadosCadastroAgendamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;


    public Agendamento( DadosCadastroAgendamento dadosCadastroAgendamento, Cliente cliente, Veiculo veiculo, Servico servico ) {
        this.descricao=dadosCadastroAgendamento.descricao();
        this.data=dadosCadastroAgendamento.data();
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.servico = servico;
    }

    public void setServico(Servico servico) {

    }
}

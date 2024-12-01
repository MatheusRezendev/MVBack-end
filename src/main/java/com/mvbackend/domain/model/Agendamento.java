package com.mvbackend.domain.model;

import com.mvbackend.domain.dto.DadosCadastroAgendamento;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    public Agendamento( DadosCadastroAgendamento dadosCadastroAgendamento) {
        this.descricao=dadosCadastroAgendamento.descricao();
        this.data=dadosCadastroAgendamento.data();
        this.cliente=dadosCadastroAgendamento.cliente();
        this.veiculo=dadosCadastroAgendamento.veiculo();
    }

    public void setServico(Servico servico) {

    }
}

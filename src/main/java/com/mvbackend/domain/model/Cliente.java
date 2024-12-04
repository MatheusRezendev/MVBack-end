package com.mvbackend.domain.model;

import com.mvbackend.domain.dto.DadosCadastroCliente;
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
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Veiculo> veiculos;


    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Agendamento> agendamentos;

    public Cliente(DadosCadastroCliente dadosCadastroCliente){
        this.nome = dadosCadastroCliente.nome();
        this.email = dadosCadastroCliente.email();
        this.telefone = dadosCadastroCliente.telefone();
        this.cpf = dadosCadastroCliente.cpf();
        this.veiculos = new ArrayList<>();
        this.agendamentos = new ArrayList<>();
    }
}

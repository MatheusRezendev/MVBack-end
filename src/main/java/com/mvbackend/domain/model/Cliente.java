package com.mvbackend.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String telefone;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Veiculo> veiculos;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Servico> servicos;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Agendamento> agendamentos;
}

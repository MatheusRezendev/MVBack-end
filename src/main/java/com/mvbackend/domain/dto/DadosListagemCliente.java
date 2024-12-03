package com.mvbackend.domain.dto;

import com.mvbackend.domain.model.Cliente;
import jakarta.validation.constraints.NotBlank;

public record DadosListagemCliente(Long id, String nome, String email, String telefone, String cpf){
    public DadosListagemCliente( Cliente cliente){
        this(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getTelefone(), cliente.getCpf());
    }
}

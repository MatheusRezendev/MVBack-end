package com.mvbackend.domain.dto;

import jakarta.validation.constraints.NotNull;

public class DadosAtualizarCliente {
    @NotNull
    Long id;

    String nome;
    String email;
    String telefone;
}

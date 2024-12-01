package com.mvbackend.domain.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCliente(
    @NotNull
    Long id,

    String nome,
    String email,
    String telefone
) {
}
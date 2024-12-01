package com.mvbackend.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCliente(
        @NotBlank
        String nome,

        @NotBlank
        String email,

        @NotBlank
        String telefone

) {
}

package com.mvbackend.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroCliente(
        @NotBlank(message = "Nome obrigatorio")
        String nome,

        @NotBlank(message = "Email deve ser válido")
        String email,

        @NotBlank(message = "Telefone deve ser válido")
        String telefone

) {
}

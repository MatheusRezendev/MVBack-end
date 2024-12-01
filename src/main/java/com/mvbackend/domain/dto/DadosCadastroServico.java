package com.mvbackend.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroServico(
        @NotBlank
        String descricao,

        @NotNull
        DadosListagemCliente cliente,

        @NotNull
        Double preco
) {
}

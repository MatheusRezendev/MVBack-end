package com.mvbackend.domain.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoServico(
        @NotNull
        Long id,

        String descricao,

        Double preco




) {
}

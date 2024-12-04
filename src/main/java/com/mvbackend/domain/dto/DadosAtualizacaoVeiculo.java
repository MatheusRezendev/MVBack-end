package com.mvbackend.domain.dto;

import com.mvbackend.domain.model.Cliente;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoVeiculo(

        @NotNull
        Long id,

        String marca,
        String modelo,
        Integer ano,
        Long clienteId
) {
}

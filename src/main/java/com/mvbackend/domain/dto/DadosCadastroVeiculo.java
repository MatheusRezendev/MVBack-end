package com.mvbackend.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroVeiculo (
        @NotBlank
        String marca,

        @NotBlank
        String modelo,

        @NotNull
        Integer ano
){
}

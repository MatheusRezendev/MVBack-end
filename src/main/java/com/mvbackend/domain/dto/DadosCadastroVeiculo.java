package com.mvbackend.domain.dto;

import com.mvbackend.domain.model.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroVeiculo (
        @NotBlank
        String marca,

        @NotBlank
        String modelo,

        @NotNull
        Integer ano,

        @NotEmpty
        Cliente cliente
){
}

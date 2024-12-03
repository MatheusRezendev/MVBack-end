package com.mvbackend.domain.dto;

import com.mvbackend.domain.model.Cliente;
import com.mvbackend.domain.model.Veiculo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroServico(
        @NotBlank
        String descricao,

        @NotNull
        Cliente cliente,

        @NotNull
        Veiculo veiculo,

        @NotNull
        Double preco
) {
}

package com.mvbackend.domain.dto;

import com.mvbackend.domain.model.Cliente;
import com.mvbackend.domain.model.Veiculo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroServico(
        @NotBlank(message = "Descrição não pode ser nula")
        String descricao,

        @NotNull(message = "Id do cliente não pode ser nulo")
        Long idCliente,

        @NotNull(message = "Id do veiculo não pode ser nulo")
        Long idVeiculo,

        @NotNull(message = "Preco não pode ser nulo")
        Double preco
) {
}

package com.mvbackend.domain.dto;

import com.mvbackend.domain.model.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DadosCadastroAgendamento(
        @NotBlank
        String descricao,

        @NotNull
        Date data,

        @NotNull
        DadosListagemCliente cliente,

        @NotNull
        DadosListagemVeiculo veiculo


) {
}

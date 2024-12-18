package com.mvbackend.domain.dto;

import com.mvbackend.domain.model.Cliente;
import com.mvbackend.domain.model.Veiculo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

public record DadosCadastroAgendamento(
        @NotBlank
        String descricao,

        @NotNull
        LocalDate data,

        @NotNull
        Long idCliente,

        @NotNull
        Long idVeiculo,

        @NotNull
        Long idServico


) {
}

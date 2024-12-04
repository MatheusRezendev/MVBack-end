package com.mvbackend.domain.dto;

import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.time.LocalDate;

public record DadosAtualizacaoAgendamento (
        @NotNull
        Long id,

        String descricao,
        LocalDate data,
        Long idVeiculo,
        Long idCliente,
        Long idServico
){}


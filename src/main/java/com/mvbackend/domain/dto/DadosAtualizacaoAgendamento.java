package com.mvbackend.domain.dto;

import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public record DadosAtualizacaoAgendamento (
        @NotNull
        Long id,

        String descricao,
        Date data,
        Long idVeiculo,
        Long idCliente,
        Long idServico
){}


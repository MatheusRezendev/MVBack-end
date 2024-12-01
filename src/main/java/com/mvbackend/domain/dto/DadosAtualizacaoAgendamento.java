package com.mvbackend.domain.dto;

import com.mvbackend.domain.model.Veiculo;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public record DadosAtualizacaoAgendamento (
        @NotNull
        Long id,

        String descricao,
        Date data,
        Veiculo veiculo
){}


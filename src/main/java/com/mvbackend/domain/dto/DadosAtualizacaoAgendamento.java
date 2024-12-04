package com.mvbackend.domain.dto;

import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.time.LocalDate;

public record DadosAtualizacaoAgendamento (
        @NotNull
        Long id,

        LocalDate data
){}


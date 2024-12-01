package com.mvbackend.domain.dto;

import com.mvbackend.domain.model.Agendamento;

import java.util.Date;

public record DadosListagemAgendamento(Long id, String descricao, Date data) {
    public DadosListagemAgendamento( Agendamento agendamento){
        this(agendamento.getId(), agendamento.getDescricao(), agendamento.getData());
    }
}

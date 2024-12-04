package com.mvbackend.domain.dto;

import com.mvbackend.domain.model.Agendamento;

import java.time.LocalDate;
import java.util.Date;

public record DadosListagemAgendamento(Long id, String descricao, LocalDate data, String nomeCliente, String modeloVeiculo, Long idServico ) {
    public DadosListagemAgendamento( Agendamento agendamento){
        this(agendamento.getId(), agendamento.getDescricao(), agendamento.getData(), agendamento.getCliente().getNome(), agendamento.getVeiculo().getModelo(), agendamento.getServico().getId());
    }
}

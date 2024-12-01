package com.mvbackend.domain.dto;

import com.mvbackend.domain.model.Veiculo;

public record DadosListagemVeiculo(Long id, String marca, String modelo, Integer ano) {
    public DadosListagemVeiculo( Veiculo veiculo ){
        this(veiculo.getId(), veiculo.getMarca(), veiculo.getModelo(), veiculo.getAno());
    }
}

package com.mvbackend.domain.dto;

import com.mvbackend.domain.model.Servico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosListagemServico(Long id, String descricao, Double preco) {
        public DadosListagemServico( Servico servico){
                this(servico.getId(), servico.getDescricao(), servico.getPreco());
        }
}

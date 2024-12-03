package com.mvbackend.domain.service;

import com.mvbackend.domain.dto.DadosAtualizacaoServico;
import com.mvbackend.domain.dto.DadosListagemServico;
import com.mvbackend.domain.model.Cliente;
import com.mvbackend.domain.model.Servico;
import com.mvbackend.domain.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public Page<Servico> findAll( Pageable pageable) {
        return servicoRepository.findAll(pageable);
    }

    public Servico findById(Long id) {
        return servicoRepository.findById(id).orElse(null);
    }

    public Servico cadastrarServico(Servico servico) {
        try{
            servicoRepository.save(servico);
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar servico");
        }
        return servico;
    }

    public Page<Servico> findByCliente( Cliente cliente, Pageable pageable) {
        Page<Servico> servicos = servicoRepository.findByCliente(cliente,pageable);
        return servicos;
    }

    public void atualizar( DadosAtualizacaoServico dadosAtualizacaoServico, Servico servico ) {
        try{
            if(dadosAtualizacaoServico.descricao() != null){
                servico.setDescricao(dadosAtualizacaoServico.descricao());
            }
            if(dadosAtualizacaoServico.preco() != null){
                servico.setPreco(dadosAtualizacaoServico.preco());
            }
        } catch (Exception e){
            throw new RuntimeException("Erro ao atualizar servico");
        }
    }

    public void deletarServico( Long id ) {
        try {
            servicoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar servico");
        }
    }

    public void save( Servico servico ) {
        servicoRepository.save(servico);
    }
}

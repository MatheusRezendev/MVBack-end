package com.mvbackend.domain.service;

import com.mvbackend.domain.dto.DadosAtualizacaoVeiculo;
import com.mvbackend.domain.model.Veiculo;
import com.mvbackend.domain.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public Page<Veiculo> findAll( Pageable pageable) {
        try {
            return veiculoRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possivel localizar os veículos!" + e);
        }
    }

    public Veiculo findById(Long id) {
        return veiculoRepository.findById(id).orElse(null);
    }

    public Page<Veiculo> findVeiculosByAno(Integer ano, Pageable pageable) {
        try{
            return veiculoRepository.findByAno(ano,pageable);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possivel localizar os veículos!" + e);
        }
    }

    public Page<Veiculo>  findVeiculosByMarca(String marca, Pageable pageable) {
        try{
            return veiculoRepository.findByMarca(marca, pageable);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possivel localizar os veículos!" + e);
        }
    }

    public void cadastrarVeiculo( Veiculo veiculo ) {
        try{
            veiculoRepository.save(veiculo);
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar veículo!" + e.getMessage());
        }
    }

    public void atualizarInformacoesVeiculo( DadosAtualizacaoVeiculo dadosAtualizacaoVeiculo, Veiculo veiculo ) {
        try{
            if(dadosAtualizacaoVeiculo.marca() != null){
                veiculo.setMarca(dadosAtualizacaoVeiculo.marca());
            }
            if(dadosAtualizacaoVeiculo.ano() != null){
                veiculo.setAno(dadosAtualizacaoVeiculo.ano());
            }
            if(dadosAtualizacaoVeiculo.modelo() != null){
                veiculo.setModelo(dadosAtualizacaoVeiculo.modelo());
            }
            if(dadosAtualizacaoVeiculo.cliente() != null){
                veiculo.setCliente(dadosAtualizacaoVeiculo.cliente());
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar veículo" + e.getMessage());
        }
    }

    public void excluirVeiculo( Long id ) {
        veiculoRepository.deleteById(id);
    }
}

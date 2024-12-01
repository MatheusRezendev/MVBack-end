package com.mvbackend.domain.service;

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

    public Optional<Veiculo> findById(Long id) {
        try{
            return veiculoRepository.findById(id);
        } catch (Exception e) {
            return Optional.empty();
        }
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

}

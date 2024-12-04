package com.mvbackend.domain.service;

import com.mvbackend.domain.dto.DadosAtualizacaoCliente;
import com.mvbackend.domain.model.Cliente;
import com.mvbackend.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Page<Cliente> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void update(DadosAtualizacaoCliente dadosAtualizacaoCliente, Cliente clienteAtualizado) {
        try {
            
            if (dadosAtualizacaoCliente.nome() != null) {
                clienteAtualizado.setNome(dadosAtualizacaoCliente.nome());
            }
            if (dadosAtualizacaoCliente.email() != null) {
                clienteAtualizado.setEmail(dadosAtualizacaoCliente.email());
            }
            if (dadosAtualizacaoCliente.telefone() != null) {
                clienteAtualizado.setTelefone(dadosAtualizacaoCliente.telefone());
            }

            clienteRepository.save(clienteAtualizado);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar cliente", e);
        }
    }


    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
}

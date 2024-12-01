package com.mvbackend.domain.service;

import com.mvbackend.domain.model.Agendamento;
import com.mvbackend.domain.model.Cliente;
import com.mvbackend.domain.model.Servico;
import com.mvbackend.domain.model.Veiculo;
import com.mvbackend.domain.repository.AgendamentoRepository;
import com.mvbackend.domain.repository.ClienteRepository;
import com.mvbackend.domain.repository.ServicoRepository;
import com.mvbackend.domain.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    public Agendamento criarAgendamento(Long clienteId, Long veiculoId, Long servicoId, Date data) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Veiculo veiculo = veiculoRepository.findById(veiculoId).orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
        Servico servico = servicoRepository.findById(servicoId).orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        Agendamento agendamento = new Agendamento();
        agendamento.setCliente(cliente);
        agendamento.setVeiculo(veiculo);
        agendamento.setServico(servico);
        agendamento.setData(data);

        return agendamentoRepository.save(agendamento);
    }

    public List<Agendamento> listarAgendamentos(){
    return agendamentoRepository.findAll();
    }

    public Agendamento buscarAgendamentoPorId(Long id){
        return agendamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
    }

    public void cancelarAgendamento(long id){
        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
    }
}

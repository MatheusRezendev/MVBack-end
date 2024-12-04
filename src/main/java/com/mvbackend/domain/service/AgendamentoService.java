package com.mvbackend.domain.service;

import com.mvbackend.domain.dto.DadosAtualizacaoAgendamento;
import com.mvbackend.domain.model.Agendamento;
import com.mvbackend.domain.model.Cliente;
import com.mvbackend.domain.model.Servico;
import com.mvbackend.domain.model.Veiculo;
import com.mvbackend.domain.repository.AgendamentoRepository;
import com.mvbackend.domain.repository.ClienteRepository;
import com.mvbackend.domain.repository.ServicoRepository;
import com.mvbackend.domain.repository.VeiculoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Date;

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
    @Autowired
    private VeiculoService veiculoService;
    @Autowired
    private ServicoService servicoService;
    @Autowired
    private ClienteService clienteService;

    public Agendamento criarAgendamento(Long clienteId, Long veiculoId, Long servicoId, LocalDate data) {
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

    public Page<Agendamento> findAll(Pageable pageable) {
        try {
            return agendamentoRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possivel localizar o agendamento" + e);
        }
    }

    public Agendamento buscarAgendamentoPorId(Long id){
        return agendamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
    }

    public void atualizarInformacoesAgendamento(DadosAtualizacaoAgendamento dadosAtualizacaoAgendamento, Agendamento agendamento) {
        try{
            if(dadosAtualizacaoAgendamento.descricao() != null){
                agendamento.setDescricao(dadosAtualizacaoAgendamento.descricao());
            }
            if (dadosAtualizacaoAgendamento.data() != null){
                agendamento.setData(dadosAtualizacaoAgendamento.data());
            }
            if (dadosAtualizacaoAgendamento.idVeiculo() != null){
                agendamento.setVeiculo(veiculoService.findById(dadosAtualizacaoAgendamento.idVeiculo()));
            }
            if (dadosAtualizacaoAgendamento.idServico() != null){
                agendamento.setServico(servicoService.findById(dadosAtualizacaoAgendamento.idServico()));
            }
            if (dadosAtualizacaoAgendamento.idVeiculo() != null){
                agendamento.setCliente(clienteService.findById(dadosAtualizacaoAgendamento.idCliente()));
            }
        } catch (Exception e){
            System.out.println("Erro ao atualizar agendamento" + e.getMessage());
        }
    }

    public void criarAgendamento(Agendamento agendamento) {
        agendamentoRepository.save(agendamento);
    }

    public Agendamento findById(Long id) {
        return agendamentoRepository.findById(id).orElse(null);
    }

    public void excluirServico(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento nao encontrado"));
        agendamentoRepository.delete(agendamento);
    }
}

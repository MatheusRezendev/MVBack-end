package com.mvbackend.controller;


import com.mvbackend.domain.dto.DadosAtualizacaoAgendamento;
import com.mvbackend.domain.dto.DadosCadastroAgendamento;
import com.mvbackend.domain.dto.DadosListagemAgendamento;
import com.mvbackend.domain.model.Agendamento;
import com.mvbackend.domain.service.AgendamentoService;
import com.mvbackend.domain.service.ClienteService;
import com.mvbackend.domain.service.ServicoService;
import com.mvbackend.domain.service.VeiculoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;
    @Autowired
    private VeiculoService veiculoService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ServicoService servicoService;

    @PostMapping
    public ResponseEntity<DadosListagemAgendamento> criarAgendamento(@RequestBody @Valid DadosCadastroAgendamento dadosCadastroAgendamento, UriComponentsBuilder uriBuilder) {
        try {
            var cliente = clienteService.findById(dadosCadastroAgendamento.idCliente());
            var veiculo = veiculoService.findById(dadosCadastroAgendamento.idVeiculo());
            var servico = servicoService.findById(dadosCadastroAgendamento.idServico());

            var agendamento = new Agendamento(dadosCadastroAgendamento, cliente, veiculo, servico);
            agendamentoService.criarAgendamento(agendamento);

            var uri = uriBuilder.path("/agendamentos/{id}").buildAndExpand(agendamento.getId()).toUri();

            return ResponseEntity.created(uri).body(new DadosListagemAgendamento(agendamento));
        } catch (EntityNotFoundException e) {
            System.out.println("Entity not found");
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemAgendamento>> listarAgendamentos(Pageable pageable){
        var page = agendamentoService.findAll(pageable).map(DadosListagemAgendamento::new);

        return ResponseEntity.ok(page);
    }
    @PutMapping
    @Transactional
    public ResponseEntity<DadosListagemAgendamento> atualizarAgendamento(@RequestBody @Valid DadosAtualizacaoAgendamento dadosAtualizacaoAgendamento){
        try{
            var agendamento = agendamentoService.findById(dadosAtualizacaoAgendamento.id());
            agendamentoService.atualizarInformacoesAgendamento(dadosAtualizacaoAgendamento, agendamento );

            return ResponseEntity.ok(new DadosListagemAgendamento(agendamento));
        } catch (EntityNotFoundException e){
            System.out.println("Entity not found");
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirAgendamento(@PathVariable Long id){
        try{
            agendamentoService.excluirServico(id);
            return ResponseEntity.noContent().build();
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}

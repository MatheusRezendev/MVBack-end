package com.mvbackend.controller;

import com.mvbackend.domain.dto.DadosCadastroServico;
import com.mvbackend.domain.dto.DadosListagemServico;
import com.mvbackend.domain.dto.DadosListagemVeiculo;
import com.mvbackend.domain.model.Cliente;
import com.mvbackend.domain.model.Servico;
import com.mvbackend.domain.service.ClienteService;
import com.mvbackend.domain.service.ServicoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<Page<DadosListagemServico>> findByCliente( @PathVariable Long clienteId, Pageable pageable) {

        var cliente = clienteService.findById(clienteId);

        try{
            Cliente cliente1 = cliente.get();
            var page = servicoService.findByCliente(cliente1,pageable).map(DadosListagemServico::new);

            return ResponseEntity.ok(page);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemServico> cadastrarServico(@RequestBody @Valid DadosCadastroServico dadosCadastroServico, UriComponentsBuilder uriBuilder) {
        try{
            Cliente cliente = clienteService.findById(dadosCadastroServico.cliente().getId());

            Servico servico = new Servico();
            servico.setDescricao(dadosCadastroServico.descricao());
            servico.setCliente(cliente);
            servico.setPreco(dadosCadastroServico.preco());

            Servico servicoSalvo = servicoService.cadastrarServico(servico);

            var uri = uriBuilder.path("/servicos/{id}").buildAndExpand(servicoSalvo.getId()).toUri();
            return ResponseEntity.created(uri).body(new DadosListagemServico(servicoSalvo));
        } catch (EntityNotFoundException e){
            System.out.println("Entity not Found");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemServico>> listarServicos(Pageable pageable) {
        var page = servicoService.findAll(pageable).map(DadosListagemServico::new);
        return ResponseEntity.ok(page);
    }
/*
    @PutMapping
    @Transactional
    public ResponseEntity<DadosListagemServico> atualizarServico(@RequestBody @Valid DadosAtualizacaoServico dadosAtualizacaoServico) {
        try {
            Servico servico = servicoService.findById(dadosAtualizacaoServico.id())
                    .orElseThrow(() -> new EntityNotFoundException("Serviço não encontrado"));

            servico.setDescricao(dadosAtualizacaoServico.descricao());
            servico.setPreco(dadosAtualizacaoServico.preco());

            servicoService.cadastrarServico(servico);

            return ResponseEntity.ok(new DadosListagemServico(servico));
        } catch (EntityNotFoundException e) {
            System.out.println("Entity not found: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirServico(@PathVariable Long id) {
        try {
            servicoService.deletarServico(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            System.out.println("Entity not found: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
*/
}

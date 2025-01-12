package com.mvbackend.controller;

import com.mvbackend.domain.dto.DadosAtualizacaoCliente;
import com.mvbackend.domain.dto.DadosCadastroCliente;
import com.mvbackend.domain.dto.DadosListagemCliente;
import com.mvbackend.domain.model.Cliente;
import com.mvbackend.domain.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Page<DadosListagemCliente>> listarTodos(Pageable pageable) {

        var page = clienteService.findAll(pageable).map(DadosListagemCliente::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemCliente> listarCliente(@PathVariable Long id){
        var cliente = clienteService.findById(id);

        if (cliente != null) {
            return ResponseEntity.ok(new DadosListagemCliente(cliente));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemCliente> salvar(@RequestBody @Valid DadosCadastroCliente dadosCadastroCliente, UriComponentsBuilder uriBuilder) {
        Cliente cliente = new Cliente(dadosCadastroCliente);
        clienteService.save(cliente);
        var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosListagemCliente(cliente));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosListagemCliente> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid DadosAtualizacaoCliente dadosAtualizacaoCliente) {

        Cliente cliente = clienteService.findById(id);

        clienteService.update(dadosAtualizacaoCliente, cliente);


        return ResponseEntity.ok(new DadosListagemCliente(cliente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        var cliente = clienteService.findById(id);
        clienteService.delete(id);

        return ResponseEntity.noContent().build();
    }
}

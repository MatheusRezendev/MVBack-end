package com.mvbackend.controller;

import com.mvbackend.domain.dto.DadosAtualizacaoVeiculo;
import com.mvbackend.domain.dto.DadosCadastroVeiculo;
import com.mvbackend.domain.dto.DadosListagemCliente;
import com.mvbackend.domain.dto.DadosListagemVeiculo;
import com.mvbackend.domain.model.Veiculo;
import com.mvbackend.domain.service.ClienteService;
import com.mvbackend.domain.service.VeiculoService;
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
@RequestMapping(("/veiculos"))
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Page<DadosListagemVeiculo>> listarVeiculos( Pageable pageable) {
        var page = veiculoService.findAll(pageable).map(DadosListagemVeiculo::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemVeiculo> buscarVeiculoPorId(@PathVariable Long id) {
        try{
            var veiculo = veiculoService.findById(id);
            return ResponseEntity.ok(new DadosListagemVeiculo(veiculo));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemVeiculo> cadastrarVeiculo( @RequestBody @Valid DadosCadastroVeiculo dadosCadastroVeiculo, UriComponentsBuilder uriBuilder){
        try{
            var cliente = clienteService.findById(dadosCadastroVeiculo.idCliente());
            var veiculo = new Veiculo(dadosCadastroVeiculo, cliente);
            veiculoService.cadastrarVeiculo(veiculo);

            var uri = uriBuilder.path("/veiculos/{id}").buildAndExpand(veiculo.getId()).toUri();

            return ResponseEntity.created(uri).body(new DadosListagemVeiculo(veiculo));
        } catch (EntityNotFoundException e) {
            System.out.println("Entity not found");
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosListagemVeiculo> atualizarVeiculo(@RequestBody @Valid DadosAtualizacaoVeiculo dadosAtualizacaoVeiculo){
        try{
            var veiculo = veiculoService.findById(dadosAtualizacaoVeiculo.id());
            veiculoService.atualizarInformacoesVeiculo(dadosAtualizacaoVeiculo, veiculo);

            return ResponseEntity.ok(new DadosListagemVeiculo(veiculo));
        } catch (EntityNotFoundException e) {
            System.out.println("Entity not found");
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirVeiculo(@PathVariable Long id){
        try{
            veiculoService.excluirVeiculo(id);
            return ResponseEntity.noContent().build();
        }
        catch (EntityNotFoundException e){
            System.out.println("Entity not found");
        }
        return ResponseEntity.badRequest().build();

    }



}

package com.mvbackend.controller;

import com.mvbackend.domain.dto.DadosAtualizacaoServico;
import com.mvbackend.domain.dto.DadosCadastroServico;
import com.mvbackend.domain.dto.DadosListagemServico;
import com.mvbackend.domain.dto.DadosListagemVeiculo;
import com.mvbackend.domain.model.Cliente;
import com.mvbackend.domain.model.Servico;
import com.mvbackend.domain.model.Veiculo;
import com.mvbackend.domain.service.ClienteService;
import com.mvbackend.domain.service.ServicoService;
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
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;


    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemServico> cadastrarServico(@RequestBody @Valid DadosCadastroServico dadosCadastroServico, UriComponentsBuilder uriBuilder) {
        try{
            Servico servico = new Servico(dadosCadastroServico);

            servicoService.save(servico);

            var uri = uriBuilder.path("/servicos/{id}").buildAndExpand(servico.getId()).toUri();
            return ResponseEntity.created(uri).body(new DadosListagemServico(servico));
        } catch (EntityNotFoundException e){
            System.out.println("Erro ao salvar servico");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemServico>> listarServicos(Pageable pageable) {
        var page = servicoService.findAll(pageable).map(DadosListagemServico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosListagemServico> atualizarServico(@RequestBody @Valid DadosAtualizacaoServico dadosAtualizacaoServico) {
        try {
            Servico servico = servicoService.findById(dadosAtualizacaoServico.id());

            servicoService.atualizar(dadosAtualizacaoServico, servico);

        } catch (EntityNotFoundException e) {
            System.out.println("Entity not found: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
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

}

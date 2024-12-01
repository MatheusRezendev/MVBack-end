package com.mvbackend.controller;

import com.mvbackend.domain.dto.DadosListagemServico;
import com.mvbackend.domain.model.Cliente;
import com.mvbackend.domain.model.Servico;
import com.mvbackend.domain.service.ClienteService;
import com.mvbackend.domain.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

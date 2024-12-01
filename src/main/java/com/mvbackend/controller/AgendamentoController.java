package com.mvbackend.controller;


import com.mvbackend.domain.model.Agendamento;
import com.mvbackend.domain.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<Agendamento> criarAgendamento(@RequestBody AgendamentoDTO agendamentoDTO){
        Agendamento agendamento = agendamentoService.criarAgendamento(
                agendamentoDTO.getClienteId(),
                agendamentoDTO.getVeiculoId(),
                agendamentoDTO.getServicoId(),
                agendamentoDTO.getData()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamento);
    }

    @GetMapping
    public List<Agendamento> listarAgendamentos(){
        return agendamentoService.listarAgendamentos();
    }

    @GetMapping("/{id}")
    public Agendamento buscarAgendamentoPorId(@PathVariable Long id){
        return agendamentoService.buscarAgendamentoPorId(id);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> cancelarAgendamento(@PathVariable Long id){
        agendamentoService.cancelarAgendamento(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

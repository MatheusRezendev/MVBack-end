package com.mvbackend.controller;

import com.mvbackend.domain.repository.VeiculoRepository;
import com.mvbackend.domain.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

}

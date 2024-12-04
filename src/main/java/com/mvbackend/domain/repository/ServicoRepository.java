package com.mvbackend.domain.repository;

import com.mvbackend.domain.model.Cliente;
import com.mvbackend.domain.model.Servico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

}

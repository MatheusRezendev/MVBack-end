package com.mvbackend.domain.repository;

import com.mvbackend.domain.model.Veiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    Page<Veiculo> findByAno( Integer ano, Pageable pageable );

    Page<Veiculo> findByMarca( String marca, Pageable pageable );
}

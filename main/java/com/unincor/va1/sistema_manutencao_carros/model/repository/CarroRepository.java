package com.unincor.va1.sistema_manutencao_carros.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unincor.va1.sistema_manutencao_carros.model.domain.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    Optional<Carro> findByPlaca(String placa);
}

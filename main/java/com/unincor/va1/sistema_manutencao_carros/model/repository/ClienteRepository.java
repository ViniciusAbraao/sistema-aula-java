package com.unincor.va1.sistema_manutencao_carros.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unincor.va1.sistema_manutencao_carros.model.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCpf(String cpf);
}

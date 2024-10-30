package com.unincor.va1.sistema_manutencao_carros.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unincor.va1.sistema_manutencao_carros.model.domain.Mecanico;

public interface MecanicoRepository extends JpaRepository<Mecanico, Long> {

    boolean existsByNomeAndTelefone(String nome, String telefone);

}

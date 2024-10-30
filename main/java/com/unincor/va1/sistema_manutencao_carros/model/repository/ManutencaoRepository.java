package com.unincor.va1.sistema_manutencao_carros.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unincor.va1.sistema_manutencao_carros.model.domain.Manutencao;

public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {

}

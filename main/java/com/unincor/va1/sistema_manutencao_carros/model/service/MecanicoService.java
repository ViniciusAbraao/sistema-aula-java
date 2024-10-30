package com.unincor.va1.sistema_manutencao_carros.model.service;

import java.util.List;

import com.unincor.va1.sistema_manutencao_carros.exceptions.MecanicoSalvarException;
import com.unincor.va1.sistema_manutencao_carros.model.domain.Mecanico;

public interface MecanicoService {

    Mecanico salvarMecanico(Mecanico mecanico) throws MecanicoSalvarException;
    List<Mecanico> listarMecanicos();
}

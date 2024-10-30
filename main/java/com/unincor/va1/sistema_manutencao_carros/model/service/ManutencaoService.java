package com.unincor.va1.sistema_manutencao_carros.model.service;

import java.util.List;

import com.unincor.va1.sistema_manutencao_carros.exceptions.ManutencaoSalvarException;
import com.unincor.va1.sistema_manutencao_carros.model.domain.Manutencao;

public interface ManutencaoService {

    Manutencao salvarManutencao(Manutencao manutencao)
            throws ManutencaoSalvarException;
    List<Manutencao> listarManutencoes();

}

package com.unincor.va1.sistema_manutencao_carros.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unincor.va1.sistema_manutencao_carros.exceptions.ManutencaoSalvarException;
import com.unincor.va1.sistema_manutencao_carros.model.domain.Manutencao;
import com.unincor.va1.sistema_manutencao_carros.model.repository.ManutencaoRepository;
import com.unincor.va1.sistema_manutencao_carros.model.service.ManutencaoService;

@Service
public class ManutencaoServiceImpl implements ManutencaoService {

    @Autowired
    private ManutencaoRepository manutencaoRepository;

    public Manutencao salvarManutencao(Manutencao manutencao)
            throws ManutencaoSalvarException {

        if (manutencao.getCarro() == null) {
            throw new ManutencaoSalvarException("Nenhum carro foi informado");
        }

        if(manutencao.getMecanico() == null) {
            throw new ManutencaoSalvarException("Nenhum mecânico foi informado!");
        }

        return manutencaoRepository.save(manutencao);
    }

    public List<Manutencao> listarManutencoes() {
        return manutencaoRepository.findAll();
    }

}

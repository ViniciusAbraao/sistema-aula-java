package com.unincor.va1.sistema_manutencao_carros.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unincor.va1.sistema_manutencao_carros.exceptions.MecanicoSalvarException;
import com.unincor.va1.sistema_manutencao_carros.model.domain.Mecanico;
import com.unincor.va1.sistema_manutencao_carros.model.repository.MecanicoRepository;
import com.unincor.va1.sistema_manutencao_carros.model.service.MecanicoService;

@Service
public class MecanicoServiceImpl implements MecanicoService {

    @Autowired
    private MecanicoRepository mecanicoRepository;

    @Override
    public Mecanico salvarMecanico(Mecanico mecanico) throws MecanicoSalvarException {
        if (mecanicoRepository.existsByNomeAndTelefone(mecanico.getNome(),
                mecanico.getTelefone())) {
                    throw new MecanicoSalvarException(
                        "Já existe um mecânico cadastrado");
        }

        return mecanicoRepository.save(mecanico);
    }

    @Override
    public List<Mecanico> listarMecanicos() {
        return mecanicoRepository.findAll();
    }
}

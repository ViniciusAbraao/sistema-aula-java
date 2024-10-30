package com.unincor.va1.sistema_manutencao_carros.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unincor.va1.sistema_manutencao_carros.exceptions.CarroSalvarException;
import com.unincor.va1.sistema_manutencao_carros.model.domain.Carro;
import com.unincor.va1.sistema_manutencao_carros.model.repository.CarroRepository;
import com.unincor.va1.sistema_manutencao_carros.model.service.CarroService;

@Service
public class CarroServiceImpl implements CarroService {

    @Autowired
    private CarroRepository carroRepository;

    @Override
    public Carro salvar(Carro carro) throws CarroSalvarException {
        var carroSalvo = carroRepository.findByPlaca(carro.getPlaca());
        if (carroSalvo.isPresent()) {
            throw new CarroSalvarException("A placa do veículo já está cadastrada!");
        }
        return carroRepository.save(carro);
    }

    @Override
    public void atualizar(Carro carro) {
        carroRepository.save(carro);
    }

    @Override
    public void deletar(Carro carro) {
        carroRepository.delete(carro);
    }

    @Override
    public List<Carro> listar() {
        return carroRepository.findAll();
    }

}

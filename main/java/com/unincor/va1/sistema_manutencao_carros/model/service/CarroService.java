package com.unincor.va1.sistema_manutencao_carros.model.service;

import java.util.List;

import com.unincor.va1.sistema_manutencao_carros.exceptions.CarroSalvarException;
import com.unincor.va1.sistema_manutencao_carros.model.domain.Carro;

public interface CarroService {

    Carro salvar(Carro carro) throws CarroSalvarException;
    void atualizar(Carro carro);
    void deletar(Carro carro);
    List<Carro> listar();
    
}

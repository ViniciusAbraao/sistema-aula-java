package com.unincor.va1.sistema_manutencao_carros.model.service;

import java.util.List;

import com.unincor.va1.sistema_manutencao_carros.exceptions.ClienteSalvarException;
import com.unincor.va1.sistema_manutencao_carros.model.domain.Cliente;

public interface ClienteService {

    Cliente salvar(Cliente cliente) throws ClienteSalvarException;
    void atualizar(Cliente cliente);
    void deletar(Cliente cliente);
    List<Cliente> listar();
}

package com.unincor.va1.sistema_manutencao_carros.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unincor.va1.sistema_manutencao_carros.exceptions.ClienteSalvarException;
import com.unincor.va1.sistema_manutencao_carros.model.domain.Cliente;
import com.unincor.va1.sistema_manutencao_carros.model.repository.ClienteRepository;
import com.unincor.va1.sistema_manutencao_carros.model.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente salvar(Cliente cliente) throws ClienteSalvarException {
        var clienteSalvo = clienteRepository.findByCpf(cliente.getCpf());
        if (clienteSalvo.isPresent()) {
            throw new ClienteSalvarException("A placa do veículo já está cadastrada!");
        }
        return clienteRepository.save(cliente);
    }

    @Override
    public void atualizar(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public void deletar(Cliente cliente) {
        clienteRepository.delete(cliente);
    }

    @Override
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

}

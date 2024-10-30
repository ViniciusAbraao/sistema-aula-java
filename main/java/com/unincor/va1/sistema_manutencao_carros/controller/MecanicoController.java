package com.unincor.va1.sistema_manutencao_carros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unincor.va1.sistema_manutencao_carros.exceptions.MecanicoSalvarException;
import com.unincor.va1.sistema_manutencao_carros.model.domain.Carro;
import com.unincor.va1.sistema_manutencao_carros.model.domain.Mecanico;
import com.unincor.va1.sistema_manutencao_carros.model.repository.MecanicoRepository;
import com.unincor.va1.sistema_manutencao_carros.model.service.MecanicoService;

@RestController
@RequestMapping("/mecanico")
public class MecanicoController {

    @Autowired
    private MecanicoService mecanicoService;
    
    @Autowired
    private MecanicoRepository mecanicoRepository;

    @PostMapping
    public Mecanico salvar(@RequestBody Mecanico mecanico)
            throws MecanicoSalvarException {
        return mecanicoService.salvarMecanico(mecanico);
    }

    @GetMapping
    public List<Mecanico> listar() {
        return mecanicoService.listarMecanicos();
    } 
    
    @GetMapping("/{id}")
    public ResponseEntity<Mecanico> buscarPorId(@PathVariable("id") Long id){
    	var mecanico = mecanicoRepository.findById(id);
    	if(!mecanico.isPresent()) {
    		return ResponseEntity.notFound().build();
    	}
    	return ResponseEntity.ok().body(mecanico.get());
    }
    
}

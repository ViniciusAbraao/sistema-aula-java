package com.unincor.va1.sistema_manutencao_carros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unincor.va1.sistema_manutencao_carros.exceptions.ManutencaoSalvarException;
import com.unincor.va1.sistema_manutencao_carros.model.domain.Carro;
import com.unincor.va1.sistema_manutencao_carros.model.domain.Comentario;
import com.unincor.va1.sistema_manutencao_carros.model.domain.Manutencao;
import com.unincor.va1.sistema_manutencao_carros.model.repository.ComentarioRepository;
import com.unincor.va1.sistema_manutencao_carros.model.repository.ManutencaoRepository;
import com.unincor.va1.sistema_manutencao_carros.model.service.ManutencaoService;

@RestController
@RequestMapping("/manutencao")
public class ManutencaoController {

    @Autowired
    private ManutencaoService manutencaoService;

    @Autowired
    private ManutencaoRepository manutencaoRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @PostMapping
    public Manutencao salvar(@RequestBody Manutencao manutencao)
            throws ManutencaoSalvarException {
        return manutencaoService.salvarManutencao(manutencao);
    }

    @GetMapping
    public List<Manutencao> listar() {
        return manutencaoService.listarManutencoes();
    }

    
    @PostMapping("/{id}/comentario")
    public ResponseEntity<Comentario> adicionarComentario(@PathVariable("id") Long id,
            @RequestBody Comentario comentario) {
        var manutencao = manutencaoRepository.findById(id);
        if(!manutencao.isPresent()) {
            return ResponseEntity.notFound().build();
        }        
        comentario.setManutencao(manutencao.get());
        return ResponseEntity.ok().body(comentarioRepository.save(comentario));
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Manutencao> buscarPorId(@PathVariable("id") Long id){
    	var manutencao = manutencaoRepository.findById(id);
    	if(!manutencao.isPresent()) {
    		return ResponseEntity.notFound().build();
    	}
    	return ResponseEntity.ok().body(manutencao.get());
    }
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

package com.unincor.va1.sistema_manutencao_carros.model.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "manutencoes")
public class Manutencao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_carro")
    private Carro carro;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_mecanico")
    private Mecanico mecanico;
    
    @NotEmpty //Para string
    @Size(max =255)
    @Column(name = "descricao_problema")
    private String descricaoProblema;
    
    @Size(max=255)
    @Column(name = "descricao_solucao")
    private String descricaoSolucao;
    
    private Double valor;

    @Column(name = "status_carro_pronto")
    private Boolean statusCarroPronto = false;

    @OneToMany(mappedBy = "manutencao")
    private Set<Comentario> comentarios = new HashSet<>();
    
}

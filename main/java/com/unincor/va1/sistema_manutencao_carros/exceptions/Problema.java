package com.unincor.va1.sistema_manutencao_carros.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

/* Só gera o json com campos não nulos */
@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class Problema {

    private Integer status;
    private OffsetDateTime dataHora;
    private String titulo;
    private List<Campo> campos;

    @Getter
    @Setter
    public static class Campo {
        private String nome;
        private String mensagem;
    }

}

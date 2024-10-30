package com.unincor.va1.sistema_manutencao_carros.exceptions;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(CarroSalvarException.class)
    public ResponseEntity<Object> handleCarroException(CarroSalvarException ex,
            WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Problema problema = new Problema();
        problema.setStatus(status.value());
        problema.setTitulo(ex.getMessage());
        problema.setDataHora(OffsetDateTime.now());

        return handleExceptionInternal(ex, problema, new HttpHeaders(),
                status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<Problema.Campo> campos = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String nome = ((FieldError) error).getField();
            String mensagem = messageSource.getMessage(error,
                    LocaleContextHolder.getLocale());

            Problema.Campo campo = new Problema.Campo();
            campo.setNome(nome);
            campo.setMensagem(mensagem);

            campos.add(campo);
        }
        return handleExceptionInternal(ex, campos, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Problema problema = new Problema();
        problema.setStatus(HttpStatus.BAD_REQUEST.value());
        problema.setTitulo("Erro na leitura do JSON. Verifique a formatação.");
        problema.setDataHora(OffsetDateTime.now());
        return handleExceptionInternal(ex, problema, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex,
            WebRequest request) {
        List<Problema.Campo> campos = new ArrayList<>();
        for (ConstraintViolation violation : ex.getConstraintViolations()) {
            String nome = violation.getPropertyPath().toString();
            String mensagem = violation.getMessage();
            Problema.Campo campo = new Problema.Campo();
            campo.setNome(nome);
            campo.setMensagem(mensagem);
            campos.add(campo);
        }
        Problema problema = new Problema();
        problema.setStatus(HttpStatus.BAD_REQUEST.value());
        problema.setTitulo("Erro de validação. Verifique os campos e tente novamente.");
        problema.setDataHora(OffsetDateTime.now());
        problema.setCampos(campos);
        return new ResponseEntity<>(problema, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}

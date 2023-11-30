package com.projetos.CestAlunos.infrastructure.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }

    // Você pode adicionar construtores adicionais, métodos ou lógicas conforme necessário
}
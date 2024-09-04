package com.projeto.estudos.projeto_estudos_java.exceptions;

public class ObjectNotFoundException extends RuntimeException {


    public ObjectNotFoundException(String msg) {
        super(msg);
    }

    public ObjectNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

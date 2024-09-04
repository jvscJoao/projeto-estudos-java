package com.projeto.estudos.projeto_estudos_java.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String field;
    private String message;
    
    public FieldMessage(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}

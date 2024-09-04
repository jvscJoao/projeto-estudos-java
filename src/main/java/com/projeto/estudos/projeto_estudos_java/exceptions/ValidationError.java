package com.projeto.estudos.projeto_estudos_java.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError {

    private Long timestamp;
	private Integer status;
	private String error;
	private String path;

    private List<FieldMessage> errors = new ArrayList<>();
    
    public ValidationError(Long timestamp, Integer status, String error, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }
    
    public void addErrors(String field, String message) {
        errors.add(new FieldMessage(field, message));
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    
    
}

package br.com.manu.model.exceptions;

import java.time.Instant;

public class ExceptionResponse {
    private Instant timesTamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public ExceptionResponse(){

    }

    public Instant getTimesTamp() {
        return timesTamp;
    }

    public void setTimesTamp(Instant timesTamp) {
        this.timesTamp = timesTamp;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

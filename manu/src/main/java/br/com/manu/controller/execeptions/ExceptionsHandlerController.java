package br.com.manu.controller.execeptions;

import br.com.manu.model.exceptions.ExceptionResponse;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.management.relation.InvalidRelationIdException;
import javax.naming.InvalidNameException;
import javax.naming.directory.InvalidAttributesException;
import java.io.InvalidObjectException;
import java.time.Instant;

@ControllerAdvice
public class ExceptionsHandlerController {

    //Essa validação é para campos vazios
    @ExceptionHandler(NoSuchFieldException.class)
    public ResponseEntity<ExceptionResponse> NotValue (NoSuchFieldException exception, HttpServletRequest request){
        ExceptionResponse error = new ExceptionResponse();
        error.setTimesTamp(Instant.now());
        error.setStatus(422);
        error.setError("Campo vazio");
        error.setMessage("Campo vazio " + exception.getMessage() + " por favor preencha o campo de maneira adequada");
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(422).body(error);

    }

    //Essa validação é para campos duplicados
    @ExceptionHandler(InvalidRelationIdException.class)
    public  ResponseEntity<ExceptionResponse> Duplicate (InvalidRelationIdException exception, HttpServletRequest request){
        ExceptionResponse error = new ExceptionResponse();
        error.setTimesTamp(Instant.now());
        error.setStatus(422);
        error.setError("Campo Existente");
        error.setMessage("Essa informação " + exception.getMessage() + " já está cadastrada");
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(422).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponse> ThereIsRegistration(DataIntegrityViolationException exception, HttpServletRequest request){
        ExceptionResponse error = new ExceptionResponse();
        error.setTimesTamp(Instant.now());
        error.setStatus(403);
        error.setError("Cadastro Relacionado");
        error.setMessage("Essa informação " + exception.getMessage() + " está relacionada a outro documento");
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(403).body(error);
    }

}

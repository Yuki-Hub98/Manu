package br.com.manu.controller.execeptions;

import br.com.manu.model.exceptions.ExceptionResponse;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ExceptionsHandlerController {

    @ExceptionHandler(NoSuchFieldException.class)
    public ResponseEntity<ExceptionResponse> NotValue (NoSuchFieldException exception, HttpServletRequest request){
        ExceptionResponse error = new ExceptionResponse();
        error.setTimesTamp(Instant.now());
        error.setStatus(422);
        error.setError("Campo vazio");
        error.setMessage("Campo vazio por favor preencha o campo de maneira adequada");
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(422).body(error);

    }

}

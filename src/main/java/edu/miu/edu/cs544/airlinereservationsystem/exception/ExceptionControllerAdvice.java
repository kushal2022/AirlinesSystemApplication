package edu.miu.edu.cs544.airlinereservationsystem.exception;

import edu.miu.edu.cs544.airlinereservationsystem.model.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {


    @Autowired
    private ErrorResponse errorResponse;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        errorResponse.setCode(400);
        String message = ex.getAllErrors()
                .stream()
                .map(n -> (String) n.getDefaultMessage())
                .collect(Collectors.joining());
        errorResponse.setMessage(message);
        return ResponseEntity.status(400).body(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        errorResponse.setCode(404);
        errorResponse.setMessage("Resource you are looking for does not exist");
        return ResponseEntity.status(404).body(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        errorResponse.setCode(500);
        errorResponse.setMessage("An error while processing your request");
        return ResponseEntity.status(500).body(errorResponse);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, SQLIntegrityConstraintViolationException ex) {
        errorResponse.setCode(500);
        errorResponse.setMessage("An error occurred while processing your request");
        return ResponseEntity.status(500).body(errorResponse);
    }
}
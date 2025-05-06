package org.example.fooddelivery.exceptions;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException exception, WebRequest request) {
        LinkedHashMap<String, String> responseBody = new LinkedHashMap<>();
        responseBody.put("instance", request.getDescription(false));
        responseBody.put("status code", String.valueOf(HttpStatus.NOT_FOUND));
        responseBody.put("detail", exception.getMessage());
        return handleExceptionInternal(exception, responseBody,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({DuplicateEntityException.class})
    public ResponseEntity<Object> handleDuplicateEntityException(DuplicateEntityException exception, WebRequest request) {
        LinkedHashMap<String, String> responseBody = new LinkedHashMap<>();
        responseBody.put("instance", request.getDescription(false));
        responseBody.put("status code", String.valueOf(HttpStatus.CONFLICT));
        responseBody.put("detail", exception.getMessage());
        return handleExceptionInternal(exception, responseBody,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({OperationNotSupportedException.class})
    public ResponseEntity<Object> handleOperationNotSupportedExceptionException(OperationNotSupportedException exception, WebRequest request) {
        LinkedHashMap<String, String> responseBody = new LinkedHashMap<>();
        responseBody.put("instance", request.getDescription(false));
        responseBody.put("status code", String.valueOf(HttpStatus.METHOD_NOT_ALLOWED));
        responseBody.put("detail", exception.getMessage());
        return handleExceptionInternal(exception, responseBody,
                new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED, request);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException exception, WebRequest request) {
        LinkedHashMap<String, String> responseBody = new LinkedHashMap<>();
        responseBody.put("type", "Validation");
        responseBody.put("instance", request.getDescription(false));
        responseBody.put("status code", String.valueOf(HttpStatus.BAD_REQUEST));
        responseBody.put("detail", exception.getMessage());
        return handleExceptionInternal(exception, responseBody,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


}

package com.epam.esm.exceptions.handler;

import com.epam.esm.exception.DuplicateEntityException;
import com.epam.esm.exception.IncorrectParameterException;
import com.epam.esm.exception.NoSuchEntityException;
import com.epam.esm.exceptions.ErrorResponse;
import com.epam.esm.exceptions.util.Translator;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

/**
 * An Exception Handler, responsible for displaying error code and messages.
 */
@RestControllerAdvice
@RequiredArgsConstructor
public class GenericExceptionHandler {

    @ExceptionHandler(DataAccessException.class)
    public final ResponseEntity<ErrorResponse> handleDataAccessException() {
        String message = Translator.toLocale("exception.DataAccessException.message");
        return getResponseEntity(new ErrorResponse(INTERNAL_SERVER_ERROR, message));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public final ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e) {
        String message = Translator.toLocale(
                "exception.HttpRequestMethodNotSupportedException.message",
                Arrays.asList(e.getMethod(), Arrays.toString(e.getSupportedMethods())));
        return getResponseEntity(new ErrorResponse(METHOD_NOT_ALLOWED, message));
    }

    @ExceptionHandler(NoSuchEntityException.class)
    public final ResponseEntity<ErrorResponse> handleNoSuchEntityException(NoSuchEntityException ex) {
        StringBuilder message = new StringBuilder(Translator.toLocale(ex.getLocalizedMessage()));
        if (ex.getId() != null) {
            message.append(", id: ").append(ex.getId());
        }
        return getResponseEntity(new ErrorResponse(BAD_REQUEST, String.valueOf(message)));
    }

    @ExceptionHandler(DuplicateEntityException.class)
    public final ResponseEntity<ErrorResponse> handleDuplicateEntityException(DuplicateEntityException ex){
        String message = Translator.toLocale(ex.getLocalizedMessage());
        return getResponseEntity(new ErrorResponse(BAD_REQUEST, message));
    }

    @ExceptionHandler(IncorrectParameterException.class)
    public final ResponseEntity<ErrorResponse> handleIncorrectParameterException(IncorrectParameterException ex){
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String,Object[]> entry: ex.getExceptionResult().getExceptionMessages().entrySet()){
            result.append(Translator.toLocale(entry.getKey())).append("; ");
        }
        return getResponseEntity(new ErrorResponse(BAD_REQUEST, String.valueOf(result)));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoHandlerFoundException() {
        String message = Translator.toLocale(
                "exception.NoHandlerFoundException.message");
        ErrorResponse response = new ErrorResponse(NOT_FOUND, message);
        return getResponseEntity(response);
    }

//    @ExceptionHandler({RuntimeException.class, Exception.class})
//    public final ResponseEntity<ErrorResponse> handleUnmappedException() {
//        String message = Translator.toLocale("exception.unmapped-exception.message");
//
//        return getResponseEntity(new ErrorResponse(INTERNAL_SERVER_ERROR, message));
//    }

    private ResponseEntity<ErrorResponse> getResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }
}

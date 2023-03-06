package com.epam.esm.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Value;
import org.springframework.http.HttpStatus;

/**
 * Class for accessing error codes and messages.
 */
@Value
public class ErrorResponse {

    int errorCode;
    String errorMessage;

    @JsonIgnore
    HttpStatus httpStatus;

    public ErrorResponse(HttpStatus httpStatus, String errorMessage) {
        this.errorCode = calcHttpStatus(httpStatus);
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

    private int calcHttpStatus(HttpStatus httpStatus){
        return httpStatus.value() * 1000 + 10 % 1000;
    }
}

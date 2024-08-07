package com.chielokacodes.userorgapp.exeption;

import com.chielokacodes.userorgapp.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(UserNotAuthorized.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponse> UserNotVerifiedException(UserNotAuthorized e) {
        ErrorResponse errorResponse = new ErrorResponse(
                false,
                HttpStatus.UNAUTHORIZED.value(),
                "Invalid Token",
                e.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);

    }

}

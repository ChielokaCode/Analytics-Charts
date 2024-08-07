package com.chielokacodes.userorgapp.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UserNotAuthorized extends RuntimeException {
    public UserNotAuthorized(String message) {
        super(message);
    }
}

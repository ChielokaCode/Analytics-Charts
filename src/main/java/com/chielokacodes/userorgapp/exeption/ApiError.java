package com.chielokacodes.userorgapp.exeption;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ApiError(
        String status,
        String message,
        int statusCode
){

}

package com.practice.QLTV.exception;

import com.practice.QLTV.DTO.Response.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<APIResponse> handlingapiResponeException(RuntimeException e) {
        APIResponse apiRespone = new APIResponse();
        apiRespone.setCode(1001);
        apiRespone.setMessage(e.getMessage());


        return ResponseEntity.badRequest().body(apiRespone);
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<APIResponse> handlingMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        APIResponse apiRespone = new APIResponse();
        apiRespone.setCode(1002);
        apiRespone.setMessage(e.getFieldError().getDefaultMessage());

        return ResponseEntity.badRequest().body(apiRespone);
    }
}

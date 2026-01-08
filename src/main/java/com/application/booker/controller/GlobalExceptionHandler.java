package com.application.booker.controller;

import com.application.booker.exception.ApplicantNotFoundException;
import com.application.booker.exception.BookNotFoundException;
import com.application.booker.response.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<?> handleBookNotFoundException(BookNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ApplicantNotFoundException.class)
    public ResponseEntity<?> handleApplicantNotFoundException(ApplicantNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String,String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> errorMap.put(error.getField(),error.getDefaultMessage()));
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), "Input Method Parameters are invalid",errorMap);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception ex){
        ProblemDetail problemDetail = null;
        if(ex instanceof BadCredentialsException){
            problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
            problemDetail.setProperty("Exception Reason","Authentication Failure - Invalid Credentials");
        }
        if(ex instanceof AccessDeniedException){
            problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
            problemDetail.setProperty("Exception Reason","Authentication Failure - Invalid Access");
        }
        if(ex instanceof SignatureException){
            problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
            problemDetail.setProperty("Exception Reason","Authentication Failure - JWT Token Signature Mismatch");
        }
        if(ex instanceof ExpiredJwtException){
            problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
            problemDetail.setProperty("Exception Reason","Authentication Failure - JWT Token Expired");
        }

        return problemDetail;
    }
}

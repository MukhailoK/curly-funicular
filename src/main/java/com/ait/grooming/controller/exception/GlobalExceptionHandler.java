package com.ait.grooming.controller.exception;

import com.ait.grooming.dto.response.Response;
import com.ait.grooming.service.exceptions.*;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.mail.MessagingException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> handlerNullPointerException(NullPointerException e) {
        Response response = new Response(e.getMessage());
        log.info(HttpStatus.INTERNAL_SERVER_ERROR.value() + ": " + e);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Response> handlerSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        Response response = new Response(e.getMessage());
        log.info(HttpStatus.BAD_REQUEST.value() + ": " + e);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Response> handleControllerViolationException(ConstraintViolationException e) {
        Response response = new Response(e.getMessage());
        log.info(HttpStatus.BAD_REQUEST.value() + ": " + e);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IsAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Response> handlerIsAlreadyExistException(IsAlreadyExistException e) {
        Response response = new Response(e.getMessage());
        log.info(HttpStatus.CONFLICT.value() + ": " + e);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handlerNotFoundException(NotFoundException e) {
        Response response = new Response(e.getMessage());
        log.info(HttpStatus.NOT_FOUND.value() + ": " + e);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MailAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Response> handleMailAuthenticationException(MailAuthenticationException e) {
        Response response = new Response(e.getMessage());
        log.info(HttpStatus.UNAUTHORIZED.value() + ": " + e);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MailSendException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> handleMailSendException(MailSendException e) {
        Response response = new Response(e.getMessage());
        log.info(HttpStatus.INTERNAL_SERVER_ERROR.value() + ": " + e);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MailParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Response> handleMailParseException(MailParseException e) {
        Response response = new Response(e.getMessage());
        log.info(HttpStatus.BAD_REQUEST.value() + ": " + e);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MessagingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> handleMessagingException(MessagingException e) {
        Response response = new Response(e.getMessage());
        log.info(HttpStatus.INTERNAL_SERVER_ERROR.value() + ": " + e);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> handleIOException(IOException e) {
        Response response = new Response(e.getMessage());
        log.info(HttpStatus.INTERNAL_SERVER_ERROR.value() + ": " + e);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Response> handlerInvalidJwtException(InvalidJwtException e) {
        Response response = new Response(e.getMessage());
        log.info(HttpStatus.UNAUTHORIZED.value() + ": " + e);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(WrongPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Response> handlerWrongPasswordException(WrongPasswordException e) {
        Response response = new Response(e.getMessage());
        log.info(HttpStatus.UNAUTHORIZED.value() + ": " + e);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(PasswordNotSameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Response> handlerPasswordNotSameException(PasswordNotSameException e) {
        Response response = new Response(e.getMessage());
        log.info(HttpStatus.BAD_REQUEST.value() + ": " + e);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Response> handlerExpiredJwtException(ExpiredJwtException e) {
        Response response = new Response(e.getMessage());
        log.info(HttpStatus.UNAUTHORIZED.value() + ": " + e);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.info(HttpStatus.BAD_REQUEST.value() + ": " + ex);
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public final ResponseEntity<Response> handleAccessDeniedException(AccessDeniedException ex) {
        log.info(HttpStatus.FORBIDDEN.value() + ": " + ex);
        return new ResponseEntity<>(new Response(ex.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Response> handleIllegalArgumentException(IllegalArgumentException e) {
        Response response = new Response(e.getMessage());
        log.info(HttpStatus.BAD_REQUEST.value() + ": " + e);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Response> handlerValidationException(ValidationException e) {
        Response response = new Response(e.getMessage());
        log.info(HttpStatus.BAD_REQUEST.value() + ": " + e);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
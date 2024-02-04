package com.ait.grooming.controller.exception;

import com.ait.grooming.dto.response.ErrorResponse;
import com.ait.grooming.service.exceptions.*;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.mail.MessagingException;
import jakarta.validation.ConstraintViolationException;
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
    public ResponseEntity<ErrorResponse> handlerNullPointerException(NullPointerException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        log.info(HttpStatus.INTERNAL_SERVER_ERROR.value() + ": " + e);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handlerSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        log.info(HttpStatus.INTERNAL_SERVER_ERROR.value() + ": " + e);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleControllerViolationException(ConstraintViolationException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        log.info(HttpStatus.BAD_REQUEST.value() + ": " + e);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IsAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handlerIsAlreadyExistException(IsAlreadyExistException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        log.info(HttpStatus.CONFLICT.value() + ": " + e);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handlerNotFoundException(NotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        log.info(HttpStatus.NOT_FOUND.value() + ": " + e);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MailAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponse> handleMailAuthenticationException(MailAuthenticationException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        log.info(HttpStatus.UNAUTHORIZED.value() + ": " + e);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MailSendException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleMailSendException(MailSendException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        log.info(HttpStatus.INTERNAL_SERVER_ERROR.value() + ": " + e);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MailParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleMailParseException(MailParseException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        log.info(HttpStatus.BAD_REQUEST.value() + ": " + e);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MessagingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleMessagingException(MessagingException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        log.info(HttpStatus.INTERNAL_SERVER_ERROR.value() + ": " + e);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleIOException(IOException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        log.info(HttpStatus.INTERNAL_SERVER_ERROR.value() + ": " + e);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponse> handlerInvalidJwtException(InvalidJwtException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        log.info(HttpStatus.UNAUTHORIZED.value() + ": " + e);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(WrongPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponse> handlerWrongPasswordException(WrongPasswordException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        log.info(HttpStatus.UNAUTHORIZED.value() + ": " + e);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(PasswordNotSameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handlerPasswordNotSameException(PasswordNotSameException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        log.info(HttpStatus.BAD_REQUEST.value() + ": " + e);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponse> handlerExpiredJwtException(InvalidJwtException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        log.info(HttpStatus.UNAUTHORIZED.value() + ": " + e);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
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
    public final ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
        log.info(HttpStatus.FORBIDDEN.value() + ": " + ex);
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        log.info(HttpStatus.BAD_REQUEST.value() + ": " + e);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    }
package com.example.spring.exceptionhandler;

import com.example.spring.exception.BadRequestException;
import com.example.spring.exception.InternalServerErrorException;
import com.example.spring.exception.NotFoundException;
import com.example.spring.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class ControllerAdviceExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorHandlerResponse> handleBadRequestException(BadRequestException ex) {
        log.error(ex.getMessage());
        ErrorHandlerResponse errorResponse = new ErrorHandlerResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), LocalDateTime.now(), ex.getStackTrace());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorHandlerResponse> handleValidationException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage());

        String message = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

        ErrorHandlerResponse errorResponse = new ErrorHandlerResponse(HttpStatus.BAD_REQUEST.value(), message, LocalDateTime.now(), ex.getStackTrace());


        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorHandlerResponse> handleInternalServerErrorException(InternalServerErrorException ex) {
        log.error(ex.getMessage());
        ErrorHandlerResponse errorResponse = new ErrorHandlerResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), LocalDateTime.now(), ex.getStackTrace());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorHandlerResponse> handleNotFoundException(NotFoundException ex) {
        log.warn(ex.getMessage());
        ErrorHandlerResponse errorResponse = new ErrorHandlerResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now(), ex.getStackTrace());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorHandlerResponse> handleUnauthorizedException(UnauthorizedException ex) {
        log.error(ex.getMessage());
        ErrorHandlerResponse errorResponse = new ErrorHandlerResponse(HttpStatus.FORBIDDEN.value(), ex.getMessage(), LocalDateTime.now(), ex.getStackTrace());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

}

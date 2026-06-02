package com.everton.clientesapi.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse error = createErrorResponseBuilder(status, "Recurso não encontrado", ex.getMessage()).build();
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse error = createErrorResponseBuilder(status, "Violação de regra de negócio", ex.getMessage()).build();
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ErrorResponse.Field> fields = ex.getBindingResult().getAllErrors().stream()
                .map(error -> ErrorResponse.Field.builder()
                        .name(((FieldError) error).getField())
                        .userMessage(error.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());

        ErrorResponse error = createErrorResponseBuilder((HttpStatus) status, "Dados inválidos", "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
                .fields(fields)
                .build();

        return handleExceptionInternal(ex, error, headers, status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse error = createErrorResponseBuilder(status, "Erro de sistema", "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir, entre em contato com o administrador.")
                .build();
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    private ErrorResponse.ErrorResponseBuilder createErrorResponseBuilder(HttpStatus status, String title, String detail) {
        return ErrorResponse.builder()
                .timestamp(OffsetDateTime.now())
                .status(status.value())
                .title(title)
                .detail(detail);
    }
}

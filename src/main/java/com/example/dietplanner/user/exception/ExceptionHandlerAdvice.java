package com.example.dietplanner.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError handleValidationException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        ApiError apiError = new ApiError(400, "Validation error", request.getServletPath());

        var bindingResult = exception.getBindingResult();

        Map<String, String> validationErrors = new HashMap<>();

        for (FieldError f : bindingResult.getFieldErrors()) {
            validationErrors.put(f.getField(), f.getDefaultMessage());
        }

        apiError.setValidationErrors(validationErrors);

        return apiError;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleConstraintViolationException(ConstraintViolationException exception,
                                                   HttpServletRequest request) throws IOException {
        ApiError apiError = new ApiError(400, "Validation error", request.getServletPath());
        Map<String, String> validationErrors = new HashMap<>();

        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();

        for (ConstraintViolation<?> constraintViolation: constraintViolations) {
            validationErrors.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
        }

        apiError.setValidationErrors(validationErrors);

        return apiError;
    }

}

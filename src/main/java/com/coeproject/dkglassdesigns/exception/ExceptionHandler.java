package com.coeproject.dkglassdesigns.exception;

import com.coeproject.dkglassdesigns.exception.custom.ResourceNotFoundException;
import com.coeproject.dkglassdesigns.exception.utility.ErrorResponse;
import com.coeproject.dkglassdesigns.exception.utility.ExceptionBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {



    @org.springframework.web.bind.annotation.ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(final Exception exception) {
        return ExceptionBuilder.buildErrorResponseRepresentation(HttpStatus.NO_CONTENT, exception.getMessage());
    }
}

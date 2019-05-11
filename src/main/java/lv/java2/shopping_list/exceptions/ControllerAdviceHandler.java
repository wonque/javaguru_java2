package lv.java2.shopping_list.exceptions;

import lv.java2.shopping_list.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
public class ControllerAdviceHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException occurredErrors) {
        List<FieldError> errors = occurredErrors.getBindingResult().getFieldErrors();

        List<ApiError> detailedErrors = errors.stream()
                .map(this::buildApiErrorFromFieldError)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(detailedErrors);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiError> handleDuplicateResourceException(DuplicateResourceException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(buildApiErrorWithCustomMessage(ex.getMessage(), HttpStatus.CONFLICT));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(buildApiErrorWithCustomMessage(ex.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(buildApiErrorWithCustomMessage(ex.getMessage(), HttpStatus.NOT_FOUND));
    }

    private ApiError buildApiErrorFromFieldError(FieldError fieldError) {
        ApiError error = new ApiError(fieldError.getField(), fieldError.getDefaultMessage(),
                HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST);
        error.setErrorDateOccurredToNow();
        return error;
    }

    private ApiError buildApiErrorWithCustomMessage(String message, HttpStatus httpStatus) {
        return new ApiError(message, httpStatus.value(), httpStatus);
    }

}

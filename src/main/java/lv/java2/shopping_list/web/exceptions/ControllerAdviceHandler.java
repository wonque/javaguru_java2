package lv.java2.shopping_list.web.exceptions;

import lv.java2.shopping_list.ApiError;
import lv.java2.shopping_list.ShoppingListError;
import org.apache.tomcat.util.ExceptionUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@ControllerAdvice
public class ControllerAdviceHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseBody
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException occurredErrors) {
        List<FieldError> errors = occurredErrors.getBindingResult().getFieldErrors();

        List<ShoppingListError> detailedErrors = errors.stream()
                .map(fieldError -> buildError(fieldError.getField(), fieldError.getDefaultMessage(), HttpStatus.BAD_REQUEST))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(detailedErrors);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND)
                , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<Object> handleDuplicateResourceException(DuplicateResourceException ex) {
        return new ResponseEntity<>(new ApiError(ex.getMessage(), HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<>(
                new ApiError(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST)
                , HttpStatus.BAD_REQUEST);
    }

    private ShoppingListError buildError(String field, String message, HttpStatus httpStatus) {
        ShoppingListError error = new ShoppingListError(message);
        error.setField(field);
        error.setHttpStatus(httpStatus);
        error.setErrorDatetimeToNow();
        return error;
    }
}

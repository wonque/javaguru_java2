package lv.java2.shopping_list;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class ShoppingListError {

    private String field;
    private String message;
    private HttpStatus httpStatus;
    private Date errorDate;

    public ShoppingListError() {
    }

    public ShoppingListError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public ShoppingListError(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getErrorDate() {
        return errorDate;
    }

    public void setErrorDatetimeToNow() {
        this.errorDate = Timestamp.valueOf(LocalDateTime.now());
    }

}

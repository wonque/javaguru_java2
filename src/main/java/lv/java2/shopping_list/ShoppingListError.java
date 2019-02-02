package lv.java2.shopping_list;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class ShoppingListError {

    private String field;
    private String errorDescription;
    private HttpStatus status;
    private Date errorDate;

    public ShoppingListError(){}


    public ShoppingListError(String field, String errorDescription) {
        this.field = field;
        this.errorDescription = errorDescription;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public Date getErrorDate() {
        return errorDate;
    }

    public void setErrorDatetimeToNow() {
        this.errorDate = Timestamp.valueOf(LocalDateTime.now());
    }
}

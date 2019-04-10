package lv.java2.shopping_list;

import org.springframework.http.HttpStatus;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class ApiError {

    private String field;
    private String message;
    private int httpStatusCode;
    private HttpStatus httpStatus;
    private Date dateOccurred;

    public ApiError(String message, int httpStatusCode, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatusCode = httpStatusCode;
        this.httpStatus = httpStatus;
    }

    public ApiError(String field, String message, int httpStatusCode, HttpStatus httpStatus) {
        this.field = field;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Date getDateOccurred() {
        return dateOccurred;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setErrorDateOccurredToNow() {
        this.dateOccurred = Timestamp.valueOf(LocalDateTime.now());
    }
}

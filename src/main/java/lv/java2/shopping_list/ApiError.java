package lv.java2.shopping_list;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class ApiError {

    private String message;
    private int httpStatusCode;
    private HttpStatus httpStatus;
    private Date dateOccurred;

    public ApiError(String message, int httpStatusCode, HttpStatus httpStatus){
        this.message = message;
        this.httpStatusCode = httpStatusCode;
        this.httpStatus = httpStatus;
        this.dateOccurred = Timestamp.valueOf(LocalDateTime.now());
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

    public void setDateOccurred(Date dateOccurred) {
        this.dateOccurred = dateOccurred;
    }
}

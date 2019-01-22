package lv.java2.shopping_list;

import org.springframework.http.HttpStatus;

public class ShoppingListError {

    private String field;
    private String errorDescription;
    private HttpStatus status;


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

    @Override
    public String toString() {
        return "Error{" +
                "field='" + field + '\'' +
                ", errorDescription='" + errorDescription + '\'' +
                ", HttpStatus ='" + status + '\'' +
                '}';
    }
}

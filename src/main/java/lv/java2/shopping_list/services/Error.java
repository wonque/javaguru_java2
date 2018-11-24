package lv.java2.shopping_list.services;

public class Error {

    private String field;
    private String errorDescription;


    public Error(String field, String errorDescription) {
        this.field = field;
        this.errorDescription = errorDescription;
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
                '}';
    }
}

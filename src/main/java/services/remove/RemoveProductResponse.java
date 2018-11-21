package services.remove;

import services.Error;

import java.util.List;

public class RemoveProductResponse {

    //    private Long productId;
    private List<Error> errors;
    private boolean isSuccess;

    public RemoveProductResponse(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public RemoveProductResponse(List<Error> errors) {
        isSuccess = false;
        this.errors = errors;
    }


    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void displayErrors() {
        errors.forEach(error -> System.out.println(error.getErrorDescription()));
    }

}

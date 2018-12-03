package lv.java2.shopping_list.services.remove.product;

import lv.java2.shopping_list.services.ShoppingListError;

import java.util.List;

public class RemoveProductResponse {

    //    private Long productId;
    private List<ShoppingListError> errors;
    private boolean isSuccess;

    public RemoveProductResponse(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public RemoveProductResponse(List<ShoppingListError> errors) {
        isSuccess = false;
        this.errors = errors;
    }

    public List<ShoppingListError> getErrors() {
        return errors;
    }

    public void setErrors(List<ShoppingListError> errors) {
        this.errors = errors;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void displayErrors() {
        errors.forEach(error -> System.out.println(error.getErrorDescription()));
    }

}

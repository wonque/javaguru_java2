package lv.java2.shopping_list.services.add.account.validation.password;

import lv.java2.shopping_list.services.ShoppingListError;

import java.util.List;

public class PasswordValidationResponse {

    private String password;
    private List<ShoppingListError> errorList;

    public PasswordValidationResponse(String password) {
        this.password = password;
    }

    public PasswordValidationResponse(List<ShoppingListError> errorList) {
        this.errorList = errorList;
    }

    public boolean isSuccess() {
        return (password != null && errorList == null || errorList.isEmpty());
    }

    public void displayErrors() {
        errorList.forEach(error -> System.out.println(error.getErrorDescription()));
    }
}

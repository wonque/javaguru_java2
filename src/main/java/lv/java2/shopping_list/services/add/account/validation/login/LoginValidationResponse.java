package lv.java2.shopping_list.services.add.account.validation.login;

import lv.java2.shopping_list.services.ShoppingListError;

import java.util.List;

public class LoginValidationResponse {

    private String login;
    private List<ShoppingListError> errors;

    public LoginValidationResponse(String login) {
        this.login = login;
    }

    public LoginValidationResponse(List<ShoppingListError> errors) {
        this.errors = errors;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isSuccess() {
        return (login != null && errors == null || errors.isEmpty());
    }

    public List<ShoppingListError> getErrors() {
        return errors;
    }

    public void displayErrors() {
        errors.forEach(error -> System.out.println(error.getErrorDescription()));
    }

}

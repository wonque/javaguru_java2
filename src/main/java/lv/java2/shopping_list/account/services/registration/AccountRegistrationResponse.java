package lv.java2.shopping_list.account.services.registration;

import lv.java2.shopping_list.account.domain.Account;
import lv.java2.shopping_list.ShoppingListError;

import java.util.List;

public class AccountRegistrationResponse {

    private Account account;
    private List<ShoppingListError> errors;

    public AccountRegistrationResponse(Account account) {
        this.account = account;
    }

    public AccountRegistrationResponse(List<ShoppingListError> errors) {
        this.errors = errors;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<ShoppingListError> getErrors() {
        return errors;
    }

    public void setErrors(List<ShoppingListError> errors) {
        this.errors = errors;
    }

    public void displayErrors(){
        errors.forEach(System.out::println);
    }

    public boolean isSuccess() {
        return (account != null && errors == null || errors.isEmpty());
    }

}

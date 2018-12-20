package lv.java2.shopping_list.services.session;

import lv.java2.shopping_list.domain.Account;
import lv.java2.shopping_list.services.ShoppingListError;

import java.util.List;

public class AccountSessionResponse {

    private Account account;
    private ShoppingListError error;

    public AccountSessionResponse(Account account) {
        this.account = account;
    }

    public AccountSessionResponse(ShoppingListError error) {
        this.error = error;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ShoppingListError getError() {
        return error;
    }

    public boolean isSuccess() {
        return (account != null && error == null);
    }

}

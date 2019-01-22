package lv.java2.shopping_list.account.services.get;

import lv.java2.shopping_list.account.domain.Account;
import lv.java2.shopping_list.ShoppingListError;

public class GetAccountResponse {

    private Account account;

    private ShoppingListError error;

    public GetAccountResponse(Account account) {
        this.account = account;
    }

    public GetAccountResponse(ShoppingListError error) {
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

    public void setError(ShoppingListError error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return (account != null && error == null);
    }
}

package lv.java2.shopping_list.services.account.get;

import lv.java2.shopping_list.domain.account.Account;
import lv.java2.shopping_list.services.ShoppingListError;

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
}

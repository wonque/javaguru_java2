package lv.java2.shopping_list.services.account.login;

import lv.java2.shopping_list.services.ShoppingListError;

public class AccountLoginResponse {

    private ShoppingListError error;
    private boolean loggedIn;

    public AccountLoginResponse(ShoppingListError error){
        this.error = error;
    }

    public AccountLoginResponse(boolean loggedIn){
        this.loggedIn = loggedIn;
    }

    public ShoppingListError getError() {
        return error;
    }

    public void setError(ShoppingListError error) {
        this.error = error;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}

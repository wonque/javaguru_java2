package lv.java2.shopping_list.shoppinglist.services;

import lv.java2.shopping_list.account.domain.Account;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ShoppingListSharedRequest {

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private Account account;

    public ShoppingListSharedRequest() {
    }

    public ShoppingListSharedRequest(Account account) {
        this.account = account;
    }

    public ShoppingListSharedRequest(Account account, String title) {
        this.account = account;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}

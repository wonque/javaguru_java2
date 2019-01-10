package lv.java2.shopping_list.services.shoppinglist.get;

import lv.java2.shopping_list.domain.account.Account;

public class GetShoppingListRequest {

    private Account account;
    private String title;

    public GetShoppingListRequest(Account account, String title) {
        this.account = account;
        this.title = title;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

package lv.java2.shopping_list.services.remove.shoppinglist;

import lv.java2.shopping_list.domain.Account;

public class RemoveShoppingListRequest {

    private Account account;
    private String listTitle;

    public RemoveShoppingListRequest(Account account, String listTitle) {
        this.account = account;
        this.listTitle = listTitle;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getListTitle() {
        return listTitle;
    }

    public void setListTitle(String listTitle) {
        this.listTitle = listTitle;
    }
}

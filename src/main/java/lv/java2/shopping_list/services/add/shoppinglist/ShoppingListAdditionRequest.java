package lv.java2.shopping_list.services.add.shoppinglist;


import lv.java2.shopping_list.domain.Account;

public class ShoppingListAdditionRequest {

    private String title;
    private Account account;

    public ShoppingListAdditionRequest(Account account, String title) {
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

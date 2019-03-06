package lv.java2.shopping_list.shoppinglist.services;

import lv.java2.shopping_list.user.domain.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ShoppingListSharedRequest {

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private User user;

    public ShoppingListSharedRequest() {
    }

    public ShoppingListSharedRequest(User user) {
        this.user = user;
    }

    public ShoppingListSharedRequest(User user, String title) {
        this.user = user;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

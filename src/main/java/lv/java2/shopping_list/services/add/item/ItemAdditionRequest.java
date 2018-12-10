package lv.java2.shopping_list.services.add.item;

import lv.java2.shopping_list.domain.ShoppingList;

public class ItemAdditionRequest {

    private ShoppingList shoppingList;
    private String title;

    public ItemAdditionRequest(ShoppingList shoppingList, String title) {
        this.shoppingList = shoppingList;
        this.title = title;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

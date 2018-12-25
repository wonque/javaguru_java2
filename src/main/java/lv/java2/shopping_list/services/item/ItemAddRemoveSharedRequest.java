package lv.java2.shopping_list.services.item;


import lv.java2.shopping_list.domain.shoppinglist.ShoppingList;

public class ItemAddRemoveSharedRequest {

    private ShoppingList shoppingList;
    private String title;

    public ItemAddRemoveSharedRequest(ShoppingList shoppingList, String title) {
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

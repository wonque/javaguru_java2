package lv.java2.shopping_list.services.update.item.description;

import lv.java2.shopping_list.domain.ShoppingList;

public class UpdateItemDescriptionRequest {

    private ShoppingList shoppingList;
    private String itemTitle;
    private String itemDescription;

    public UpdateItemDescriptionRequest(ShoppingList shoppingList, String itemTitle, String itemDescription) {
        this.shoppingList = shoppingList;
        this.itemTitle = itemTitle;
        this.itemDescription = itemDescription;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
}

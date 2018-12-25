package lv.java2.shopping_list.services.shoppinglist.removal;

import lv.java2.shopping_list.services.ShoppingListError;

public class ShoppingListRemovalResponse {

    private ShoppingListError error;
    private boolean listRemoved;

    public ShoppingListRemovalResponse(ShoppingListError error) {
        listRemoved = false;
        this.error = error;
    }

    public ShoppingListRemovalResponse(boolean listRemoved) {
        this.listRemoved = listRemoved;
    }

    public ShoppingListError getError() {
        return error;
    }

    public void setError(ShoppingListError error) {
        this.error = error;
    }

    public boolean isListRemoved() {
        return listRemoved;
    }

    public void setListRemoved(boolean listRemoved) {
        this.listRemoved = listRemoved;
    }
}

package lv.java2.shopping_list.services.remove.shoppinglist;

import lv.java2.shopping_list.services.ShoppingListError;

public class RemoveShoppingListResponse {

    private ShoppingListError error;
    private boolean listRemoved;

    public RemoveShoppingListResponse(ShoppingListError error) {
        listRemoved = false;
        this.error = error;
    }

    public RemoveShoppingListResponse(boolean listRemoved) {
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

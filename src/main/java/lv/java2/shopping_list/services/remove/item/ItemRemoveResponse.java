package lv.java2.shopping_list.services.remove.item;

import lv.java2.shopping_list.services.ShoppingListError;

public class ItemRemoveResponse {

    private ShoppingListError error;
    private boolean removed;

    public ItemRemoveResponse(ShoppingListError error) {
        this.error = error;
        this.removed = false;
    }

    public ItemRemoveResponse(boolean removed) {
        this.removed = removed;
    }

    public ShoppingListError getError() {
        return error;
    }

    public void setError(ShoppingListError error) {
        this.error = error;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }
}

package lv.java2.shopping_list.item.services.removal;

import lv.java2.shopping_list.ShoppingListError;

public class ItemRemoveResponse {

    private ShoppingListError error;
    private boolean removed;
    private int totalItemsRemoved;

    public ItemRemoveResponse(ShoppingListError error) {
        this.error = error;
        this.removed = false;
    }

    public ItemRemoveResponse(boolean removed) {
        this.removed = true;
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

    public void setTotalItemsRemoved(int totalItemsRemoved) {
        this.totalItemsRemoved = totalItemsRemoved;
    }

    public int getTotalItemsRemoved() {
        return totalItemsRemoved;
    }
}

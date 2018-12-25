package lv.java2.shopping_list.services.item.update;

import lv.java2.shopping_list.services.ShoppingListError;

import java.util.List;

public class ItemUpdateSharedResponse {

    private ShoppingListError error;
    private boolean updated;

    public ItemUpdateSharedResponse(ShoppingListError error) {
        this.error = error;
        this.updated = false;
    }

    public ItemUpdateSharedResponse(boolean isUpdated) {
        this.updated = isUpdated;
    }

    public ShoppingListError getError() {
        return error;
    }

    public void setError(ShoppingListError error) {
        this.error = error;
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }
}

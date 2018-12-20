package lv.java2.shopping_list.services.item.update;

import lv.java2.shopping_list.services.ShoppingListError;

import java.util.List;

public class ItemUpdateSharedResponse {

    private List<ShoppingListError> errors;
    private boolean updated;

    public ItemUpdateSharedResponse(List<ShoppingListError> errors) {
        this.errors = errors;
        this.updated = false;
    }

    public ItemUpdateSharedResponse(boolean isUpdated) {
        this.updated = isUpdated;
    }

    public List<ShoppingListError> getErrors() {
        return errors;
    }

    public void setError(List<ShoppingListError> errors) {
        this.errors = errors;
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }
}

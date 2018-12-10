package lv.java2.shopping_list.services.update.item.description;

import lv.java2.shopping_list.services.ShoppingListError;

public class UpdateItemDescriptionResponse {

    private ShoppingListError error;
    private boolean updated;

    public UpdateItemDescriptionResponse(ShoppingListError error) {
        this.error = error;
        this.updated = false;
    }

    public UpdateItemDescriptionResponse(boolean isUpdated) {
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

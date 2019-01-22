package lv.java2.shopping_list.shoppinglist.services.removal;

import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.shoppinglist.services.ShoppingListResponse;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ShoppingListRemovalResponse extends ShoppingListResponse {

    private List<ShoppingListError> errors;
    private HttpStatus status;
    private boolean listRemoved;

    public ShoppingListRemovalResponse(List<ShoppingListError> errors) {
        this.errors = errors;
    }

    public ShoppingListRemovalResponse(boolean listRemoved) {
        this.listRemoved = listRemoved;
    }

    public List<ShoppingListError> getErrors() {
        return errors;
    }

    public void setError(List<ShoppingListError> errors) {
        this.errors = errors;
    }

    public boolean isListRemoved() {
        return listRemoved;
    }

    public void setListRemoved(boolean listRemoved) {
        this.listRemoved = listRemoved;
    }

    @Override
    public boolean isSuccess() {
        return (errors == null || errors.isEmpty() && listRemoved);
    }
}

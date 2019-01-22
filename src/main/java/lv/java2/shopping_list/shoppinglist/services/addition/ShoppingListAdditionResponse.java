package lv.java2.shopping_list.shoppinglist.services.addition;

import lv.java2.shopping_list.shoppinglist.domain.ShoppingList;
import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.shoppinglist.services.ShoppingListResponse;

import java.util.List;

public class ShoppingListAdditionResponse extends ShoppingListResponse {

    private ShoppingList shoppingList;
    private List<ShoppingListError> errors;

    public ShoppingListAdditionResponse(List<ShoppingListError> errors) {
        this.errors = errors;
    }

    public ShoppingListAdditionResponse(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public boolean isSuccess() {
        return (shoppingList != null && errors == null || errors.isEmpty());
    }

    public ShoppingList getAddedList() {
        return shoppingList;
    }

    public List<ShoppingListError> getShoppingListErrors() {
        return errors;
    }

    public void setShoppingListErrors(List<ShoppingListError> errors) {
        this.errors = errors;
    }

    public void displayErrors() {
        errors.forEach(error -> System.out.println(error.getErrorDescription()));
    }

}

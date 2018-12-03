package lv.java2.shopping_list.services.add.shoppinglist;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.services.ShoppingListError;

import java.util.List;

public class ShoppingListAdditionResponse {


    private Long addedListId;
    private List<ShoppingListError> errors;

    public ShoppingListAdditionResponse(List<ShoppingListError> errors) {
        this.errors = errors;
    }

    public ShoppingListAdditionResponse(Long addedListId) {
        this.addedListId = addedListId;
    }

    public boolean isSuccess() {
        return (addedListId != null && errors == null || errors.isEmpty());
    }

    public Long getAddedListId() {
        return addedListId;
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

package lv.java2.shopping_list.services.add.shoppinglist;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.services.Error;

import java.util.List;

public class ShoppingListAdditionResponse {


    private ShoppingList addedList;
    private List<Error> errors;

    public ShoppingListAdditionResponse(List<Error> errors) {
        this.errors = errors;
    }

    public ShoppingListAdditionResponse(ShoppingList addedList) {
        this.addedList = addedList;
    }

    public boolean isSuccess() {
        return (addedList != null && errors == null || errors.isEmpty());
    }

    public ShoppingList getAddedList() {
        return addedList;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public void displayErrors() {
        errors.forEach(error -> System.out.println(error.getErrorDescription()));
    }

}

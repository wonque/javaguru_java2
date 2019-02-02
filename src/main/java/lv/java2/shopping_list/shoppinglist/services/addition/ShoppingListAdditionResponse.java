package lv.java2.shopping_list.shoppinglist.services.addition;

import lv.java2.shopping_list.shoppinglist.domain.ShoppingList;
import lv.java2.shopping_list.ShoppingListError;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ShoppingListAdditionResponse extends ResourceSupport {

    private ShoppingList shoppingList;
    private List<ShoppingListError> errors;
    private HttpStatus httpStatus;

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

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public List<ShoppingListError> getErrors() {
        return errors;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

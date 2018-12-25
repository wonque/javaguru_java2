package lv.java2.shopping_list.services.item.addition;

import lv.java2.shopping_list.domain.item.ShoppingListItem;
import lv.java2.shopping_list.services.ShoppingListError;

import java.util.List;

public class ItemAdditionResponse {

    private ShoppingListItem shoppingListItem;
    private List<ShoppingListError> errorList;

    public ItemAdditionResponse(ShoppingListItem shoppingListItem) {
        this.shoppingListItem = shoppingListItem;
    }

    public ItemAdditionResponse(List<ShoppingListError> errorList) {
        this.errorList = errorList;
    }

    public ShoppingListItem getShoppingListItem() {
        return shoppingListItem;
    }

    public void setShoppingListItem(ShoppingListItem shoppingListItem) {
        this.shoppingListItem = shoppingListItem;
    }

    public List<ShoppingListError> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<ShoppingListError> errorList) {
        this.errorList = errorList;
    }

    public boolean isSuccess() {
        return (shoppingListItem != null && errorList == null || errorList.isEmpty());
    }
}

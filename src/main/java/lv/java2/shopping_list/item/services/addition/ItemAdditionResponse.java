package lv.java2.shopping_list.item.services.addition;

import lv.java2.shopping_list.item.domain.Item;
import lv.java2.shopping_list.ShoppingListError;

import java.util.List;

public class ItemAdditionResponse {

    private Item item;
    private List<ShoppingListError> errorList;

    public ItemAdditionResponse(Item item) {
        this.item = item;
    }

    public ItemAdditionResponse(List<ShoppingListError> errorList) {
        this.errorList = errorList;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<ShoppingListError> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<ShoppingListError> errorList) {
        this.errorList = errorList;
    }

    public boolean isSuccess() {
        return (item != null && errorList == null || errorList.isEmpty());
    }
}

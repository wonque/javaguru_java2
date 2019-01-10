package lv.java2.shopping_list.services.shoppinglist.get;

import lv.java2.shopping_list.domain.shoppinglist.ShoppingList;
import lv.java2.shopping_list.services.ShoppingListError;

import java.util.List;

public class GetShoppingListResponse {

    private ShoppingList shoppingList;
    private List<ShoppingList> listOfShoppingLists;
    private ShoppingListError error;

    public GetShoppingListResponse(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public GetShoppingListResponse(List<ShoppingList> listOfShoppingLists) {
        this.listOfShoppingLists = listOfShoppingLists;
    }

    public GetShoppingListResponse(ShoppingListError error) {
        this.error = error;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public List<ShoppingList> getListOfShoppingLists() {
        return listOfShoppingLists;
    }

    public void setListOfShoppingLists(List<ShoppingList> listOfShoppingLists) {
        this.listOfShoppingLists = listOfShoppingLists;
    }

    public ShoppingListError getError() {
        return error;
    }

    public void setError(ShoppingListError error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return (shoppingList != null || listOfShoppingLists != null) && error == null;
    }
}

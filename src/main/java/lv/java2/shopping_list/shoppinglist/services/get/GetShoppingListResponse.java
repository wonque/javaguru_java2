package lv.java2.shopping_list.shoppinglist.services.get;

import lv.java2.shopping_list.shoppinglist.domain.ShoppingList;
import lv.java2.shopping_list.ShoppingListError;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;

import java.util.List;

public class GetShoppingListResponse extends ResourceSupport {

    private ShoppingList shoppingList;
    private List<ShoppingList> listOfShoppingLists;
    private ShoppingListError error;
    private HttpStatus httpStatus;

    public GetShoppingListResponse(ShoppingList shoppingList, HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.shoppingList = shoppingList;
    }

    public GetShoppingListResponse(List<ShoppingList> listOfShoppingLists, HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.listOfShoppingLists = listOfShoppingLists;
    }

    public GetShoppingListResponse(ShoppingListError error, HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
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

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

}

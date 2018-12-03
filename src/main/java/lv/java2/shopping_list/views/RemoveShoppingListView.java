package lv.java2.shopping_list.views;

import lv.java2.shopping_list.services.remove.product.RemoveProductRequest;
import lv.java2.shopping_list.services.remove.product.RemoveProductResponse;
import lv.java2.shopping_list.services.remove.product.RemoveProductService;
import lv.java2.shopping_list.services.remove.shoppinglist.RemoveShoppingListRequest;
import lv.java2.shopping_list.services.remove.shoppinglist.RemoveShoppingListResponse;
import lv.java2.shopping_list.services.remove.shoppinglist.RemoveShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RemoveShoppingListView {

    @Autowired
    private RemoveShoppingListService removeService;

    @Autowired
    private UserInputGetters inputGetters;

    public void execute() {
        String listName = inputGetters.getShoppingListTitleFromUser();
        RemoveShoppingListRequest request = new RemoveShoppingListRequest(listName);
        RemoveShoppingListResponse response = removeService.remove(request);
        if (response.isSuccess()) {
            System.out.println("Shopping list " + listName + " removed!\n");
        } else {
            System.out.println("Shopping list " + listName + " not removed!\n");
            response.displayErrors();
        }
    }
}


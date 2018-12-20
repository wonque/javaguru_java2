package lv.java2.shopping_list.views.shoppinglist;

import lv.java2.shopping_list.services.session.AccountSessionResponse;
import lv.java2.shopping_list.services.shoppinglist.ShoppingListSharedRequest;
import lv.java2.shopping_list.services.shoppinglist.addition.ShoppingListAdditionResponse;
import lv.java2.shopping_list.services.shoppinglist.addition.ShoppingListAdditionService;
import lv.java2.shopping_list.views.UserInputGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingListAdditionView {

    private ShoppingListAdditionResponse listAdditionResponse;

    @Autowired
    private ShoppingListAdditionService shoppingListService;


    @Autowired
    private UserInputGetter inputGetters;

    public ShoppingListAdditionResponse execute(AccountSessionResponse sessionResponse) {
        System.out.println("Add shopping list process started.");
        String listTitle = inputGetters.getShoppingListTitleFromUser();
        ShoppingListSharedRequest newRequest = new ShoppingListSharedRequest(sessionResponse.getAccount(), listTitle);
        listAdditionResponse = shoppingListService.addList(newRequest);
        if (listAdditionResponse.isSuccess()) {
            System.out.println("Shopping list " + listTitle.toUpperCase() + " added!\n");
        } else {
            listAdditionResponse.displayErrors();
        }
        return listAdditionResponse;
    }
}

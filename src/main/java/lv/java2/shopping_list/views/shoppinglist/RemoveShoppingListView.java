package lv.java2.shopping_list.views.shoppinglist;

import lv.java2.shopping_list.services.session.AccountSessionResponse;
import lv.java2.shopping_list.services.shoppinglist.ShoppingListSharedRequest;
import lv.java2.shopping_list.services.shoppinglist.removal.RemoveShoppingListResponse;
import lv.java2.shopping_list.services.shoppinglist.removal.RemoveShoppingListService;
import lv.java2.shopping_list.views.UserInputGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RemoveShoppingListView {

    @Autowired
    private RemoveShoppingListService removeService;
    @Autowired
    private UserInputGetter inputGetter;

    public RemoveShoppingListResponse execute(AccountSessionResponse sessionResponse) {
        System.out.println("Remove shopping list process started.\n");
        System.out.println("This will also remove all items in shopping list!");
        String listTitle = inputGetter.getShoppingListTitleFromUser();
        ShoppingListSharedRequest removeRequest = new ShoppingListSharedRequest(sessionResponse.getAccount(), listTitle);
        RemoveShoppingListResponse removeResponse = removeService.remove(removeRequest);
        if (removeResponse.isListRemoved()) {
            System.out.println("Shopping list removed!");
        } else {
            System.out.println("Unable to remove shopping list!\n");
            System.out.println(removeResponse.getError().getField());
            System.out.println(removeResponse.getError().getErrorDescription());
        }
        return removeResponse;
    }

}

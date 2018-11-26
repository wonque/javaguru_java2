package lv.java2.shopping_list.views;

import lv.java2.shopping_list.services.AddShoppingListRequest;
import lv.java2.shopping_list.services.AddShoppingListResponse;
import lv.java2.shopping_list.services.AddShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddShoppingListView {

    @Autowired
    private AddShoppingListService shoppingListService;

    @Autowired
    private UserInputGetters inputGetters;

    public void execute() {
        System.out.println("Add shopping list process started.");
        String title = inputGetters.getShoppingListTitleFromUser();
        AddShoppingListRequest newRequest = new AddShoppingListRequest(title);
        AddShoppingListResponse response = shoppingListService.addList(newRequest);
        if (response.isSuccess()) {
            System.out.println("Shopping list " + title.toUpperCase() + " created!\n");
            System.out.println("ID: " + response.getListId());

        } else {
            response.displayErrors();
        }
    }

}

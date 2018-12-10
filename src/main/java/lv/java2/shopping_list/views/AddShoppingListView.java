package lv.java2.shopping_list.views;

import lv.java2.shopping_list.services.add.shoppinglist.ShoppingListAdditionRequest;
import lv.java2.shopping_list.services.add.shoppinglist.ShoppingListAdditionResponse;
import lv.java2.shopping_list.services.add.shoppinglist.ShoppingListAdditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddShoppingListView {

    private ShoppingListAdditionResponse listAdditionResponse;

    @Autowired
    private ShoppingListAdditionService shoppingListService;


    @Autowired
    private UserInputGetters inputGetters;

//    public Long execute() {
//        System.out.println("Add shopping list process started.");
//        String listTitle = inputGetters.getShoppingListTitleFromUser();
//        ShoppingListAdditionRequest newRequest = new ShoppingListAdditionRequest(listTitle);
//        listAdditionResponse = shoppingListService.addList(newRequest);
//        if (listAdditionResponse.isSuccess()) {
//            System.out.println("Shopping list " + listTitle.toUpperCase() + " added!\n");
//        } else {
//            listAdditionResponse.displayErrors();
//        }
//        return listAdditionResponse.getAddedListId();

}

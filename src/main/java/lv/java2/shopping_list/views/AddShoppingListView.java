package lv.java2.shopping_list.views;

import lv.java2.shopping_list.services.add.shoppinglist.ShoppingListAdditionRequest;
import lv.java2.shopping_list.services.add.shoppinglist.ShoppingListAdditionResponse;
import lv.java2.shopping_list.services.add.shoppinglist.ShoppingListAdditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddShoppingListView {

    private ShoppingListAdditionResponse response;

    @Autowired
    private ShoppingListAdditionService shoppingListService;

    @Autowired
    private AddProductView addProductView;

    @Autowired
    private UserInputGetters inputGetters;

    public void execute() {
        System.out.println("Add shopping list process started.");
        String title = inputGetters.getShoppingListTitleFromUser();
        ShoppingListAdditionRequest newRequest = new ShoppingListAdditionRequest(title);
        response = shoppingListService.addList(newRequest);
        if (response.isSuccess()) {
            System.out.println("Shopping list " + title.toUpperCase() + " created!\n");
            executeAddProductProcess(title);

        } else {
            response.displayErrors();
        }
    }

    private void printAddProductMenu(String listTitle) {
        System.out.println("1: Add new product to " + listTitle.toUpperCase());
        System.out.println("0: End shopping list addition");
    }

    private void executeAddProductProcess(String listTitle) {
        while (true) {
            printAddProductMenu(listTitle);
            int option = inputGetters.getAddProductProcessItemFromUser();
            if (option == 0) {
                break;
            }
            switch (option) {
                case 1:
                    addProductView.execute(response.getAddedList());
                    break;
            }
        }
    }
}

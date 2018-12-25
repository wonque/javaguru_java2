package lv.java2.shopping_list.views.item;

import lv.java2.shopping_list.domain.shoppinglist.ShoppingList;
import lv.java2.shopping_list.services.item.ItemAddRemoveSharedRequest;
import lv.java2.shopping_list.services.item.addition.ItemAdditionResponse;
import lv.java2.shopping_list.services.item.addition.ItemAdditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ItemAdditionView {

    @Autowired
    private ItemAdditionService itemAdditionService;

    public void execute(ShoppingList shoppingList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter item title: ");
        String title = scanner.nextLine();
        ItemAddRemoveSharedRequest additionRequest = new ItemAddRemoveSharedRequest(shoppingList, title);
        ItemAdditionResponse response = itemAdditionService.addItem(additionRequest);
        if (response.isSuccess()) {
            System.out.println("Item added!");
        } else {
            response.getErrorList().forEach(error -> System.out.println(error));
        }
    }

}

package lv.java2.shopping_list.views.shoppinglist;

import lv.java2.shopping_list.db.ShoppingListRepository;
import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.session.AccountSessionResponse;
import lv.java2.shopping_list.services.shoppinglist.ShoppingListStatusValidator;

import java.util.Optional;
import java.util.Scanner;

public class ModifyShoppingListView {

    private ShoppingListStatusValidator statusValidator;
    private ShoppingListRepository listRepository;


    public Optional<ShoppingList> getListToModify(AccountSessionResponse response) {
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();
        System.out.println("Enter shopping list title: ");
        Optional<ShoppingList> founded = listRepository.findByAccountAndTitle(response.getAccount(), title);
        if (founded.isPresent()) {
            Optional<ShoppingListError> error = statusValidator.execute(founded.get());
            if (!error.isPresent()) {
                return founded;
            } else {
                System.out.println(error.get().getField());
                System.out.println(error.get().getErrorDescription());
            }
        } else {
            System.out.println("Shopping list not found!");
        }
        return Optional.empty();
    }
}

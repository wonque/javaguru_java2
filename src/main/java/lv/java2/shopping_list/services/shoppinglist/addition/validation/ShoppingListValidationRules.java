package lv.java2.shopping_list.services.shoppinglist.addition.validation;

import lv.java2.shopping_list.db.ShoppingListRepository;
import lv.java2.shopping_list.domain.account.Account;
import lv.java2.shopping_list.domain.shoppinglist.ShoppingList;
import lv.java2.shopping_list.services.ShoppingListError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ShoppingListValidationRules {

    @Autowired
    private ShoppingListRepository shoppingListRepository;


    public Optional<ShoppingListError> duplicateEntryRule(Account account, String title) {
        Optional<ShoppingList> shoppingList = shoppingListRepository.findByAccountAndTitle(account, title);
        if (shoppingList.isPresent()) {
            ShoppingListError error = new ShoppingListError("title",
                    "Shopping list with title " + title + " already in database!");
            return Optional.of(error);
        } else {
            return Optional.empty();
        }
    }
}

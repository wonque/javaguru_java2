package lv.java2.shopping_list.shoppinglist.services.validation.rules;

import lv.java2.shopping_list.shoppinglist.repository.ShoppingListRepository;
import lv.java2.shopping_list.account.domain.Account;
import lv.java2.shopping_list.shoppinglist.domain.ShoppingList;
import lv.java2.shopping_list.ShoppingListError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DuplicateEntryRule {

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    public Optional<ShoppingListError> validate(Account account, String title) {
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
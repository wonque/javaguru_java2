package lv.java2.shopping_list.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AddShoppingListRules {

    @Autowired
    private ErrorFactory errorFactory;

    public Optional<Error> emptyTitleRule(String title) {
        if (title == null || title.isEmpty() || title.matches("\\s+")) {
            Error error = errorFactory.createNewError("title", "Empty title not allowed!");
            return Optional.of(error);
        } else {
            return Optional.empty();
        }
    }
}

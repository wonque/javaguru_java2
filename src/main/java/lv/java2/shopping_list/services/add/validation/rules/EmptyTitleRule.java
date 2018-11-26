package lv.java2.shopping_list.services.add.validation.rules;

import lv.java2.shopping_list.services.Error;
import lv.java2.shopping_list.services.ErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmptyTitleRule {

    @Autowired
    private ErrorFactory factory;

    public Optional<Error> execute(String title) {
        if (title == null || title.isEmpty() || title.matches("\\s+")) {
            Error error = factory.createNewError("title", "Empty title not allowed!");
            return Optional.of(error);
        } else {
            return Optional.empty();
        }
    }
}

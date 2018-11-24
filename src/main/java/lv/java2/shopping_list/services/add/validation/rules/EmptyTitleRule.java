package lv.java2.shopping_list.services.add.validation.rules;

import lv.java2.shopping_list.services.Error;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmptyTitleRule {

    public Optional<Error> execute(String title) {
        if (title == null || title.isEmpty() || title.matches("\\s+")) {
            Error error = new Error("title", "Empty title not allowed!");
            return Optional.of(error);
        } else {
            return Optional.empty();
        }
    }
}

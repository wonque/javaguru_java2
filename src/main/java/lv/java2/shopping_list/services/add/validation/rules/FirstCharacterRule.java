package lv.java2.shopping_list.services.add.validation.rules;

import org.springframework.stereotype.Component;
import lv.java2.shopping_list.services.Error;

import java.util.Optional;

@Component
public class FirstCharacterRule {

    public Optional<Error> execute(String title) {
        if (title.substring(0, 1).matches("\\W")) {
            Error error = new Error("title", "First character in title must be a word-type character");
            return Optional.of(error);
        } else {
            return Optional.empty();
        }
    }

}



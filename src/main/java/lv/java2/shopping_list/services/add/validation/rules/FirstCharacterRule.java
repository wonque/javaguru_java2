package lv.java2.shopping_list.services.add.validation.rules;

import lv.java2.shopping_list.services.ErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lv.java2.shopping_list.services.Error;

import java.util.Optional;

@Component
public class FirstCharacterRule {

    @Autowired
    private ErrorFactory factory;

    public Optional<Error> execute(String title) {
        if (title.substring(0, 1).matches("\\W")) {
            Error error = factory.createNewError("title", "First character in title must be a word-type character");
            return Optional.of(error);
        } else {
            return Optional.empty();
        }
    }

}



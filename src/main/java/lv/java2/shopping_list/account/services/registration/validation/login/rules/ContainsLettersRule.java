package lv.java2.shopping_list.account.services.registration.validation.login.rules;

import lv.java2.shopping_list.ShoppingListError;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ContainsLettersRule extends LoginRules {

    public Optional<ShoppingListError> apply(String login) {
        Pattern pattern = Pattern.compile(".*[a-zA-Z].*");
        Matcher matcher = pattern.matcher(login);
        if (!matcher.find()) {
            return Optional.of(createError("Login must contain letters"));
        }
        return Optional.empty();
    }
}

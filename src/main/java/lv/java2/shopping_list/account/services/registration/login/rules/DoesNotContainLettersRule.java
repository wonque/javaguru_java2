package lv.java2.shopping_list.account.services.registration.login.rules;

import lv.java2.shopping_list.ShoppingListError;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoesNotContainLettersRule extends LoginRules {

    public Optional<ShoppingListError> execute (String login) {
        Pattern pattern = Pattern.compile(".*[a-zA-Z].*");
        Matcher matcher = pattern.matcher(login);
        if (!matcher.find()) {
            return Optional.of(createError("Login must contain letters and numbers"));
        }
        return Optional.empty();
    }
}

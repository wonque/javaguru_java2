package lv.java2.shopping_list.services.account.registration.validation.rules;

import lv.java2.shopping_list.db.AccountRepository;
import lv.java2.shopping_list.domain.account.Account;
import lv.java2.shopping_list.services.ShoppingListError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class LoginRules {

    @Autowired
    private AccountRepository accountRepository;

    public Optional<ShoppingListError> lessThan5Length(String login) {
        if (login.length() < 5) {
            return Optional.of(createError("Login too short! Minimum length is 5 symbols"));
        } else {
            return Optional.empty();
        }
    }

    public Optional<ShoppingListError> containsAtSignAndDotSign(String login) {
        if (!login.contains("@") && !login.contains(".") || login.contains("@") && !login.contains(".")
                || !login.contains("@") && login.contains(".")) {
            return Optional.of(createError("Login is not an email address!"));
        } else {
            return Optional.empty();
        }
    }

    public Optional<ShoppingListError> doesNotContainLetters(String login) {
        Pattern pattern = Pattern.compile(".*[a-zA-Z].*");
        Matcher matcher = pattern.matcher(login);
        if (!matcher.find()) {
            return Optional.of(createError("Login must contain letters and numbers"));
        }
        return Optional.empty();
    }

    public Optional<ShoppingListError> duplicateLogin(String login) {
        if (login != null) {
            Optional<Account> founded = accountRepository.findByLogin(login);
            if (founded.isPresent()) {
                return Optional.of(createError("Account with this login is already registered"));
            }
        }
        return Optional.empty();
    }

    public Optional<ShoppingListError> longerThat100symbols(String login) {
        if (login != null && login.length() > 100) {
            return Optional.of(createError("Login is too long - maximum 100 symbols"));
        }
        return Optional.empty();
    }

    private ShoppingListError createError(String description) {
        return new ShoppingListError("login", description);
    }

}

package lv.java2.shopping_list.services.add.account.validation.rules;

import lv.java2.shopping_list.db.AccountRepository;
import lv.java2.shopping_list.domain.Account;
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

    public Optional<ShoppingListError> emptyLogin(String login) {
        if (login == null || login.isEmpty() || login.matches("\\s+")) {
            ShoppingListError error = new ShoppingListError("login", "Empty login not allowed!");
            return Optional.of(error);
        } else {
            return Optional.empty();
        }
    }

    public Optional<ShoppingListError> lessThan5Length(String login) {
        if (login.length() < 5) {
            ShoppingListError error = new ShoppingListError("login", "Login too short! Minimum length is 5 symbols");
            return Optional.of(error);
        } else {
            return Optional.empty();
        }
    }

    public Optional<ShoppingListError> containsAtSignAndDotSign(String login) {
        if (!login.contains("@") && !login.contains(".") || login.contains("@") && !login.contains(".")
                || !login.contains("@") && login.contains(".")) {
            ShoppingListError error = new ShoppingListError("login", "Login is not an email address!");
            return Optional.of(error);
        } else {
            return Optional.empty();
        }
    }

    public Optional<ShoppingListError> doesNotContainLetters(String login) {
        Pattern pattern = Pattern.compile(".*[a-zA-Z].*");
        Matcher matcher = pattern.matcher(login);
        if (!matcher.find()) {
            ShoppingListError error = new ShoppingListError("login", "Login must contain letters and numbers");
            return Optional.of(error);
        }
        return Optional.empty();
    }


    public Optional<ShoppingListError> duplicateLogin(String login) {
        if (login != null) {
            Optional<Account> account = accountRepository.findAccountByLogin(login);
            if (account.isPresent()) {
                ShoppingListError error = new ShoppingListError("login", "Account " + login + " already registered!");
                return Optional.of(error);
            }
        }
        return Optional.empty();
    }
}

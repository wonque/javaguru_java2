package lv.java2.shopping_list.account.services.registration.login.rules;

import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.account.domain.Account;
import lv.java2.shopping_list.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class DuplicateLoginRule extends LoginRules {

    @Autowired
    private AccountRepository accountRepository;

    public Optional<ShoppingListError> execute(String login) {
        if (login != null) {
            Optional<Account> founded = accountRepository.findByLogin(login);
            if (founded.isPresent()) {
                return Optional.of(createError("Account with this login is already registered"));
            }
        }
        return Optional.empty();
    }
}

package lv.java2.shopping_list.services.session;

import lv.java2.shopping_list.db.AccountRepository;
import lv.java2.shopping_list.domain.Account;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.add.account.validation.rules.LoginRules;
import lv.java2.shopping_list.services.add.account.validation.rules.PasswordRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class AccountSessionService {


    @Autowired private LoginRules loginRules;
    @Autowired private PasswordRules passwordRules;
    @Autowired private AccountRepository repository;

    @Transactional
    public AccountSessionResponse openSession(String login, String password) {
        Optional<Account> optionalAccount = repository.findByLoginAndPass(login, password);
        if (optionalAccount.isPresent()) {
            return new AccountSessionResponse(optionalAccount.get());
        } else {
            ShoppingListError error = new ShoppingListError("Login or password",
                    "Wrong login or password!");
            return new AccountSessionResponse(error);
        }
    }

}

package lv.java2.shopping_list.services.account.login;

import lv.java2.shopping_list.db.AccountRepository;
import lv.java2.shopping_list.domain.account.Account;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.account.AccountPasswordHashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountLoginService {

    @Autowired
    private AccountPasswordHashService hashService;
    @Autowired
    private AccountRepository accountRepository;

    public AccountLoginResponse login(AccountLoginRequest request) {
        Optional<Account> founded = accountRepository.findByLogin(request.getLogin());
        if (!founded.isPresent()) {
            return new AccountLoginResponse(new ShoppingListError("login",
                    "Account not found"));
        }
        boolean validated =
                hashService.matchPasswords(founded.get().getPassword(),
                        request.getPassword());
        if (validated) {
            return new AccountLoginResponse(validated);
        } else {
            return new AccountLoginResponse(new ShoppingListError("password"
                    , "Wrong password"));
        }
    }
}

package lv.java2.shopping_list.account.services.login;

import lv.java2.shopping_list.account.repository.AccountRepository;
import lv.java2.shopping_list.account.domain.Account;
import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.account.services.password.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountLoginService {

    @Autowired
    private PasswordService passwordService;
    @Autowired
    private AccountRepository accountRepository;

    public AccountLoginResponse login(AccountLoginRequest request) {
        Optional<Account> founded = accountRepository.findByLogin(request.getLogin());
        if (!founded.isPresent()) {
            return new AccountLoginResponse(new ShoppingListError("login",
                    "Account not found"));
        }
        boolean validated =
                passwordService.validatePasswords(founded.get().getPassword(),
                        request.getPassword());
        if (validated) {
            return new AccountLoginResponse(validated);
        } else {
            return new AccountLoginResponse(new ShoppingListError("password"
                    , "Wrong password"));
        }
    }
}

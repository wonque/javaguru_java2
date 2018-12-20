package lv.java2.shopping_list.services.session;

import lv.java2.shopping_list.db.AccountRepository;
import lv.java2.shopping_list.db.orm.AccountRepositoryImpl;
import lv.java2.shopping_list.domain.Account;
import lv.java2.shopping_list.services.ShoppingListError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountSessionService {

    @Autowired
    private AccountRepository repository;

    public AccountSessionResponse execute(AccountSessionRequest request) {
        Optional<Account> optionalAccount = repository.findByLoginAndPass(request.getLogin(), request.getPassword());
        if (optionalAccount.isPresent()) {
            return new AccountSessionResponse(optionalAccount.get());
        } else {
            ShoppingListError error = new ShoppingListError("Login or password",
                    "Wrong login or password!");
            return new AccountSessionResponse(error);
        }
    }

}

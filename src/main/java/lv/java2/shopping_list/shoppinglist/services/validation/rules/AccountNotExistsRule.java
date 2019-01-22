package lv.java2.shopping_list.shoppinglist.services.validation.rules;

import lv.java2.shopping_list.account.repository.AccountRepository;
import lv.java2.shopping_list.account.domain.Account;
import lv.java2.shopping_list.ShoppingListError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountNotExistsRule {

    @Autowired
    private AccountRepository accountRepository;

    public Optional<ShoppingListError> validate(Account account) {
        Optional<Account> founded = accountRepository.findById(account.getId());
        if (!founded.isPresent()) {
            ShoppingListError error = new ShoppingListError("account",
                    "Account does not exists!");
            error.setStatus(HttpStatus.NOT_FOUND);
            return Optional.of(error);
        }
        return Optional.empty();
    }
}

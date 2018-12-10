package lv.java2.shopping_list.services.add.account.registration;

import lv.java2.shopping_list.db.AccountRepository;
import lv.java2.shopping_list.domain.Account;
import lv.java2.shopping_list.domain.builders.AccountBuilder;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.add.account.validation.AccountRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountRegistrationService {

    @Autowired
    private AccountBuilder accountBuilder;

    @Autowired
    private AccountRepository repository;

    @Autowired
    private AccountRegistrationValidator validator;

    public AccountRegistrationResponse register(AccountRegistrationRequest request) {
        List<ShoppingListError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
           return new AccountRegistrationResponse(errors);
        }
        Account account = accountBuilder.buildInstance(request.getLogin(),
                request.getMainPassword(), request.getUserName());
        repository.addToBase(account);
        return new AccountRegistrationResponse(account);
    }
}

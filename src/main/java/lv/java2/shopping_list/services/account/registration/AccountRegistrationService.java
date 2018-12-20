package lv.java2.shopping_list.services.account.registration;

import lv.java2.shopping_list.db.AccountRepository;
import lv.java2.shopping_list.domain.Account;
import lv.java2.shopping_list.domain.factories.AccountFactory;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.account.validation.AccountRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountRegistrationService {

    @Autowired
    private AccountFactory accountFactory;

    @Autowired
    private AccountRepository repository;

    @Autowired
    private AccountRegistrationValidator validator;

    public AccountRegistrationResponse register(AccountRegistrationRequest request) {
        List<ShoppingListError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
           return new AccountRegistrationResponse(errors);
        }
        Account account = accountFactory.buildInstance(request.getLogin(),
                request.getMainPassword(), request.getUserName());
        repository.addToBase(account);
        return new AccountRegistrationResponse(account);
    }
}

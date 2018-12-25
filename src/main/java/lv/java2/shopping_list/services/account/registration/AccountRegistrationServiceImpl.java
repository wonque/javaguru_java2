package lv.java2.shopping_list.services.account.registration;

import lv.java2.shopping_list.db.AccountRepository;
import lv.java2.shopping_list.domain.account.Account;
import lv.java2.shopping_list.domain.account.AccountFactory;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.account.AccountPasswordHashService;
import lv.java2.shopping_list.services.account.registration.validation.AccountRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountRegistrationServiceImpl implements AccountRegistrationService {

    @Autowired
    private AccountFactory accountFactory;
    @Autowired
    private AccountRepository repository;
    @Autowired
    private AccountRegistrationValidator validator;
    @Autowired
    private AccountPasswordHashService passwordHashService;

    public AccountRegistrationResponse register(AccountRegistrationRequest request) {
        List<ShoppingListError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AccountRegistrationResponse(errors);
        }
        String hashedPass = passwordHashService.hashPassword(request.getPlainTextPassword());
        Account account = accountFactory.buildInstance(
                request.getLogin(),
                hashedPass,
                request.getUserName());
        repository.addToBase(account);
        return new AccountRegistrationResponse(account);
    }

}

package lv.java2.shopping_list.account.services.registration;

import lv.java2.shopping_list.account.repository.AccountRepository;
import lv.java2.shopping_list.account.domain.Account;
import lv.java2.shopping_list.account.domain.AccountFactory;
import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.account.services.password.PasswordService;
import lv.java2.shopping_list.account.services.registration.validation.AccountRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountRegistrationServiceImpl implements AccountRegistrationService {

    @Autowired
    private AccountFactory accountFactory;
    @Autowired
    private AccountRepository repository;
    @Autowired
    private AccountRegistrationValidator validator;
    @Autowired
    private PasswordService passwordService;

    @Transactional
    public AccountRegistrationResponse register(AccountRegistrationRequest request) {
        List<ShoppingListError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AccountRegistrationResponse(errors);
        }
        String processedPassword = passwordService.processPassword(request.getPassword());
        Account account = accountFactory.buildInstance(
                request.getLogin(),
                processedPassword,
                request.getUserName());
        repository.save(account);
        return new AccountRegistrationResponse(account);
    }
}

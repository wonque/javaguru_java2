package lv.java2.shopping_list.account.services.registration.validation;

import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.account.services.registration.validation.password.PasswordSpellingValidator;
import lv.java2.shopping_list.account.services.registration.validation.login.LoginSpellingValidator;
import lv.java2.shopping_list.web.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountRegistrationValidatorImpl implements AccountRegistrationValidator {

    @Autowired
    private LoginSpellingValidator loginSpellingValidator;
    @Autowired
    private PasswordSpellingValidator passwordSpellingValidator;
    @Autowired
    private DuplicateAccountRule duplicateAccountRule;

    @Override
    public List<ShoppingListError> validate(AccountDTO accountDTO) {
        List<ShoppingListError> validationErrors = new ArrayList<>();
        loginSpellingValidator.validate(accountDTO.getEmail()).stream()
                .forEach(validationErrors::add);
        passwordSpellingValidator.validate(accountDTO.getPassword())
                .stream().forEach(validationErrors::add);
        if (validationErrors.isEmpty()) {
            duplicateAccountRule.apply(accountDTO.getEmail())
                    .ifPresent(validationErrors::add);
        }
        return validationErrors;
    }
}

package lv.java2.shopping_list.account.services.registration;

import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.account.domain.Account;
import lv.java2.shopping_list.account.repository.AccountRepository;
import lv.java2.shopping_list.account.services.registration.validation.AccountRegistrationValidator;
import lv.java2.shopping_list.web.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static lv.java2.shopping_list.account.domain.AccountFactory.createAccount;

@Service
public class AccountRegistrationServiceImpl implements AccountRegistrationService {

    @Autowired
    private AccountRepository repository;
    @Autowired
    private AccountRegistrationValidator validator;

    @Transactional
    public AccountRegistrationResponse register(AccountDTO accountDTO) {
//        List<ShoppingListError> errors = validator.validate(accountDTO);
//        if (!errors.isEmpty()) {
//            return new AccountRegistrationResponse(errors, HttpStatus.BAD_REQUEST);
//        }
        String hashedPass = hashPassword(accountDTO.getPassword());
        Account account = createAccount().withEmail(accountDTO.getEmail())
                .withPassword(hashedPass)
                .withUsername(accountDTO.getUsername())
                .build();
        repository.save(account);
        accountDTO.setId(account.getId());
        return new AccountRegistrationResponse(accountDTO, HttpStatus.CREATED);
    }


    private String hashPassword(String userPassword) {
        return BCrypt.hashpw(userPassword, BCrypt.gensalt());
    }

}

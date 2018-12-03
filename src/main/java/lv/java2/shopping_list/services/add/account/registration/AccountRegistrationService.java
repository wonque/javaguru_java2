package lv.java2.shopping_list.services.add.account.registration;

import lv.java2.shopping_list.db.AccountRepository;
import lv.java2.shopping_list.domain.Account;
import lv.java2.shopping_list.domain.builders.AccountBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountRegistrationService {

    @Autowired
    private AccountBuilder accountBuilder;

    @Autowired
    private AccountRepository repository;

    public boolean execute(String login, String password, String userName) {
        Account newEntry = accountBuilder.buildInstance(login, password, userName);
        repository.addToBase(newEntry);
        if (newEntry.getId() == null) {
            return false;
        }
        return true;
    }
}

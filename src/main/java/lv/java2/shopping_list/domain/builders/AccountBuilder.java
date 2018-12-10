package lv.java2.shopping_list.domain.builders;

import lv.java2.shopping_list.domain.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountBuilder {

    public Account buildInstance(String login, String password, String nickname) {
        Account account = new Account(login, password);
        account.setUserName(nickname);
        return account;
    }

}

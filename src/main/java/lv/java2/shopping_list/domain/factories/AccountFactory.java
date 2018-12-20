package lv.java2.shopping_list.domain.factories;

import lv.java2.shopping_list.domain.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountFactory {

    public Account buildInstance(String login, String password, String nickname) {
        Account account = new Account(login, password);
        account.setUserName(nickname);
        return account;
    }

}

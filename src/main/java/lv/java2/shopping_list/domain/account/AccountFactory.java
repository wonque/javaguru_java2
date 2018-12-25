package lv.java2.shopping_list.domain.account;

import org.springframework.stereotype.Component;

@Component
public class AccountFactory {

    public Account buildInstance(String login, String password, String nickname) {
        Account account = new Account();
        account.setLogin(login);
        account.setUserName(nickname);
        account.setPassword(password);
        return account;
    }

}

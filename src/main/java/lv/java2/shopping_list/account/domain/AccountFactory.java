package lv.java2.shopping_list.account.domain;

import org.springframework.stereotype.Component;

@Component
public class AccountFactory {

    public Account buildInstance(String loginAsEmail, String password, String nickname) {
        Account account = new Account();
        account.setLogin(loginAsEmail);
        account.setUserName(nickname);
        account.setPassword(password);
        return account;
    }

}

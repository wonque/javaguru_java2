package lv.java2.shopping_list.account.domain;

import org.springframework.stereotype.Component;

@Component
public class AccountFactory {

    private String email;
    private String password;
    private String userName;

    private AccountFactory(){};

    public static AccountFactory createAccount(){
        return new AccountFactory();
    }

    public Account build() {
        Account account = new Account();
        account.setEmail(email);
        account.setUserName(userName);
        account.setPassword(password);
        return account;
    }

    public AccountFactory withEmail (String email){
        this.email = email;
        return this;
    }

    public AccountFactory withPassword(String password){
        this.password = password;
        return this;
    }

    public AccountFactory withUsername(String userName){
        this.userName = userName;
        return this;
    }

}

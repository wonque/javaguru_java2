package lv.java2.shopping_list.user.domain;

import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    private String email;
    private String password;
    private String userName;

    private UserFactory() {
    }

    ;

    public synchronized static UserFactory createAccount() {
        return new UserFactory();
    }

    public User build() {
        User user = new User();
        user.setEmail(email);
        user.setUsername(userName);
        user.setPassword(password);
        return user;
    }

    public UserFactory withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserFactory withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserFactory withUsername(String userName) {
        this.userName = userName;
        return this;
    }

}

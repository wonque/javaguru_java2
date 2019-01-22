package lv.java2.shopping_list.account.services.registration;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AccountRegistrationRequest {

    @NotNull (message = "Login cannot be null")
    @NotEmpty (message = "Login cannot be empty")
    private String login;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    private String userName;

    public AccountRegistrationRequest() {
    }

    public AccountRegistrationRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}

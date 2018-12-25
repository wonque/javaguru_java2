package lv.java2.shopping_list.services.account.registration;

public class AccountRegistrationRequest {

    private String login;
    private String plainTextPassword;
    private String userName;

    public AccountRegistrationRequest(String login, String plainTextPassword) {
        this.login = login;
        this.plainTextPassword = plainTextPassword;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPlainTextPassword() {
        return plainTextPassword;
    }

    public void setPlainTextPassword(String plainTextPassword) {
        this.plainTextPassword = plainTextPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}

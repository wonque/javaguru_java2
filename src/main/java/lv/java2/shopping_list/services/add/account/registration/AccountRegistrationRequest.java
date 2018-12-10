package lv.java2.shopping_list.services.add.account.registration;

public class AccountRegistrationRequest {

    private String login;
    private String mainPassword;
    private String passwordToMatch;
    private String userName;

    public AccountRegistrationRequest(String login, String mainPassword, String passwordToMatch) {
        this.login = login;
        this.mainPassword = mainPassword;
        this.passwordToMatch = passwordToMatch;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMainPassword() {
        return mainPassword;
    }

    public void setMainPassword(String mainPassword) {
        this.mainPassword = mainPassword;
    }

    public String getPasswordToMatch() {
        return passwordToMatch;
    }

    public void setPasswordToMatch(String passwordToMatch) {
        this.passwordToMatch = passwordToMatch;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

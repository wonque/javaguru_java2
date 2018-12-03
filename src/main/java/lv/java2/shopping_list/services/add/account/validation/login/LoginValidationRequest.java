package lv.java2.shopping_list.services.add.account.validation.login;

public class LoginValidationRequest {

    private String login;

    public LoginValidationRequest(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}

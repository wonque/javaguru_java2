package lv.java2.shopping_list.services.session;

public class AccountSessionRequest {

    private String login;
    private String password;

    public AccountSessionRequest (String login, String password){
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    protected String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

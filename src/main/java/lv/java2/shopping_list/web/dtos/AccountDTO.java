package lv.java2.shopping_list.web.dtos;


public class AccountDTO {

    private Long id;
    private String login;
    private String userName;
    private String password;

    public Long getId() {
        return id;
    }

    public AccountDTO() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

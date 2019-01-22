package lv.java2.shopping_list.account.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(schema = "java2", name = "accounts")
public class Account {

    public Account() {
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LOGIN", nullable = false)
    @NotNull
    private String loginAsEmail;

    @Column(name = "PASSWORD", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    private String password;

    @Column(name = "USERNAME")
    private String userName;

    @CreationTimestamp
    @Column(name = "DATE_CREATED")
    private Date dateCreated;
//    public Account(String login, String password) {
//        this.login = login;
//        this.password = password;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return loginAsEmail;
    }

    public void setLogin(String login) {
        this.loginAsEmail = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(loginAsEmail, account.loginAsEmail) &&
                Objects.equals(password, account.password) &&
                Objects.equals(userName, account.userName) &&
                Objects.equals(dateCreated, account.dateCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loginAsEmail, password, userName, dateCreated);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", login='" + loginAsEmail + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}

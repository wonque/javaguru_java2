package lv.java2.shopping_list.domain;

import javax.persistence.*;
import java.sql.Timestamp;

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
    private String login;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "DATE_CREATED")
    private Timestamp dateCreated;

    @Column(name = "LAST_SESSION_DATE")
    private Timestamp lastSessionDate;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private AccountStatus status;


    public Account(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Timestamp getLastSessionDate() {
        return lastSessionDate;
    }

    public void setLastSessionDate(Timestamp lastSessionDate) {
        this.lastSessionDate = lastSessionDate;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

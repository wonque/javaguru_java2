package lv.java2.shopping_list.domain.account;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
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
    private String login;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "USERNAME")
    private String userName;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_SESSION_DATE")
    private Date lastSessionDate;

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
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastSessionDate() {
        return lastSessionDate;
    }

    public void setLastSessionDate(Timestamp lastSessionDate) {
        this.lastSessionDate = lastSessionDate;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
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
                Objects.equals(login, account.login) &&
                Objects.equals(password, account.password) &&
                Objects.equals(userName, account.userName) &&
                Objects.equals(dateCreated, account.dateCreated) &&
                Objects.equals(lastSessionDate, account.lastSessionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, userName, dateCreated, lastSessionDate);
    }
}

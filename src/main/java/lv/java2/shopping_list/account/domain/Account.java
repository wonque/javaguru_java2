package lv.java2.shopping_list.account.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.hateoas.Identifiable;

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

    @Column(name = "EMAIL", nullable = false)
    @NotNull
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    private String password;

    @Column(name = "USERNAME")
    private String username;

    @CreationTimestamp
    @Column(name = "DATE_CREATED")
    private Date dateCreated;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
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

    public void setUserName(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(email, account.email) &&
                Objects.equals(password, account.password) &&
                Objects.equals(username, account.username) &&
                Objects.equals(dateCreated, account.dateCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, username, dateCreated);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", login='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + username + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}

package lv.java2.shopping_list.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(schema = "java2", name = "users")
public class User {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @CreationTimestamp
    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return dateCreated != null ? dateCreated.equals(user.dateCreated) : user.dateCreated == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, dateCreated);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + email + '\'' +
                ", password='" + password + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}

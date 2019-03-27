package lv.java2.shopping_list.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;


public class UserDTO {

    //    @Null(groups = NewEntry.PrimaryCheck.class, message = "ID field must be null")
//    @NotNull(groups = ExistingEntry.class)
    private Long userId;

    @NotBlank(message = "This field cannot be blank")
    @Email(regexp = ".+@.+\\..+", message = "Please provide a valid email address")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "This field cannot be blank")
    @Size(min = 6, message = "Password must be longer than six symbols")
//    @JsonIgnore
    private String password;

    private String username;
    private Date dateCreated;


    public UserDTO() {
    }

    public UserDTO(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public UserDTO(String email, String username, Long userId) {
        this.email = email;
        this.username = username;
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){return false;}
        UserDTO that = (UserDTO) obj;
        return Objects.equals(userId, that.userId) && Objects.equals(email, that.email)
                && Objects.equals(username, that.username)
                && Objects.equals(dateCreated, that.dateCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, password, username, dateCreated);
    }
}

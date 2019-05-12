package lv.java2.shopping_list.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lv.java2.shopping_list.controllers.validation.CreateEntity;
import lv.java2.shopping_list.controllers.validation.UpdateEntity;
import lv.java2.shopping_list.dto.validation.UserEmailCostraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;


public class UserDTO {

    @NotNull(groups = UpdateEntity.class)
    @Null(groups = CreateEntity.class)
    private Long userId;

    @UserEmailCostraint(groups = {CreateEntity.class, UpdateEntity.class})
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(groups = CreateEntity.class, message = "This field cannot be blank")
    @Size(groups = CreateEntity.class, min = 6, message = "Password must be longer than six symbols")
    private String password;

    private Date dateCreated;


    public UserDTO() {
    }

    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserDTO(String email, Long userId) {
        this.email = email;
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
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserDTO that = (UserDTO) obj;
        return Objects.equals(userId, that.userId) && Objects.equals(email, that.email)
                && Objects.equals(dateCreated, that.dateCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, password, dateCreated);
    }
}

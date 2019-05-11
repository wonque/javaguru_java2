package lv.java2.shopping_list.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lv.java2.shopping_list.controllers.validation.CreateEntity;
import lv.java2.shopping_list.controllers.validation.UpdateEntity;
import lv.java2.shopping_list.domain.ShoppingListStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.Objects;

public class ShoppingListDTO {

    @NotNull(groups = {UpdateEntity.class})
    @Null(groups = {CreateEntity.class})
    private Long id;

    @NotBlank(groups = CreateEntity.class,message = "This field cannot be blank")
    private String title;
    private String category;
    private ShoppingListStatus status;
    private Long userId;
    private Date dateCreated;
    private Date dateModified;

    public ShoppingListDTO() {
    }

    public ShoppingListDTO(Long userId, String title) {
        this.userId = userId;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ShoppingListStatus getStatus() {
        return status;
    }

    public void setStatus(ShoppingListStatus status) {
        this.status = status;
    }

    @JsonIgnore
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingListDTO dto = (ShoppingListDTO) o;
        return Objects.equals(id, dto.id) &&
                Objects.equals(title, dto.title) &&
                Objects.equals(category, dto.category) &&
                status == dto.status &&
                Objects.equals(userId, dto.userId) &&
                Objects.equals(dateCreated, dto.dateCreated) &&
                Objects.equals(dateModified, dto.dateModified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, category, status, userId, dateCreated, dateModified);
    }

    @Override
    public String toString() {
        return "ShoppingListDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", status=" + status +
                ", userId=" + userId +
                ", dateCreated=" + dateCreated +
                ", dateModified=" + dateModified +
                '}';
    }
}

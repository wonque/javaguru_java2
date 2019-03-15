package lv.java2.shopping_list.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lv.java2.shopping_list.domain.ShoppingListStatus;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class ShoppingListDTO {

    private Long id;

    @NotBlank
    private String title;
    private String category;
    private ShoppingListStatus status;
    private Long userId;
    private Date dateCreated;
    private Date dateModified;

    public ShoppingListDTO() {
    }

//    public ShoppingListDTO (Long userId, String title){
//        this.userId = userId;
//        this.title = title;
//    }

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
}

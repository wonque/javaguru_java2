package lv.java2.shopping_list.dto;

import lv.java2.shopping_list.controllers.validation.CreateEntity;
import lv.java2.shopping_list.controllers.validation.UpdateEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Objects;

public class ShoppingListItemDto {

    @Null(groups = CreateEntity.class)
    @NotNull(groups = UpdateEntity.class)
    private Long id;
    private Long listId;
    private Long itemId;
    @NotBlank
    private String title;
    private Integer quantity;
    private double price;

    public ShoppingListItemDto(Long listId, Long itemId, String title, Integer quantity, double price) {
        this.listId = listId;
        this.itemId = itemId;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public Long getListId() {
        return listId;
    }

    public Long getItemId() {
        return itemId;
    }

    public String getTitle() {
        return title;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingListItemDto that = (ShoppingListItemDto) o;
        return Double.compare(that.price, price) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(listId, that.listId) &&
                Objects.equals(itemId, that.itemId) &&
                Objects.equals(title, that.title) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, listId, itemId, title, quantity, price);
    }
}

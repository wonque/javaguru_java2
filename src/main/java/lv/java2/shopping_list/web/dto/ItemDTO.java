package lv.java2.shopping_list.web.dto;

import java.util.Objects;

public class ItemDTO {

    private String title;
    private String description;
    private Double price;

    public ItemDTO() {
    }

    public ItemDTO(String title, String description, Double price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDTO itemDTO = (ItemDTO) o;
        return Objects.equals(title, itemDTO.title) &&
                Objects.equals(description, itemDTO.description) &&
                Objects.equals(price, itemDTO.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, price);
    }
}

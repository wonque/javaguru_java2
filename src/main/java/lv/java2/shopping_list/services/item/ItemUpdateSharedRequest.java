package lv.java2.shopping_list.services.item;

import lv.java2.shopping_list.domain.item.ShoppingListItem;

import java.math.BigDecimal;

public class ItemUpdateSharedRequest {

    private ShoppingListItem item;
    private String title;
    private String description;
    private BigDecimal price;
    private int quantity;


    public ItemUpdateSharedRequest(ShoppingListItem item) {
        this.item = item;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setBigDecimalPrice(double price) {
        this.price = BigDecimal.valueOf(price);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ShoppingListItem getShoppingListItm() {
        return item;
    }

    public String getTitle() {
        return title;
    }

    public void setShoppingListItem(ShoppingListItem item) {
        this.item = item;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

package lv.java2.shopping_list.domain;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(schema = "java2", name = "shopping_list_item")
public class ShoppingListItem {

    public ShoppingListItem() {
    }

    @Id
    @Column(name = "ITEM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @ManyToOne
    @JoinColumn(name = "SHOPPING_LIST_ID", nullable = false)
    private ShoppingList shoppingList;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "PRICE")
    private BigDecimal price;


    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingListItem item = (ShoppingListItem) o;
        return quantity == item.quantity &&
                Objects.equals(itemId, item.itemId) &&
                Objects.equals(shoppingList, item.shoppingList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, shoppingList, quantity);
    }

    @Override
    public String toString() {
        return "ShoppingListItem{" +
                "itemId=" + itemId +
                ", shoppingList=" + shoppingList +
                ", userEnteredProductTitle='" +
                ", quantity=" + quantity +
                '}';
    }
}

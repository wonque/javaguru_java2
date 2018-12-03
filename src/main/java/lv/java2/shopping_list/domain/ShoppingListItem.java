package lv.java2.shopping_list.domain;


import javax.persistence.*;
import java.util.Objects;

import static com.sun.tools.doclint.Entity.or;

//@Entity
//@Table(schema = "java2", name = "shopping_list_item")
public class ShoppingListItem {

    public ShoppingListItem() {
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "SHOPPING_LIST_ID", nullable = false)
    private ShoppingList shoppingList;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    @Column(name = "QUANTITY", nullable = false)
    private int quantity;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingListItem item = (ShoppingListItem) o;
        return quantity == item.quantity &&
                Objects.equals(itemId, item.itemId) &&
                Objects.equals(shoppingList, item.shoppingList) &&
                Objects.equals(product, item.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, shoppingList, product, quantity);
    }

    @Override
    public String toString() {
        return "ShoppingListItem{" +
                "itemId=" + itemId +
                ", shoppingList=" + shoppingList +
                ", product=" + product +
                ", userEnteredProductTitle='"  +
                ", quantity=" + quantity +
                '}';
    }
}

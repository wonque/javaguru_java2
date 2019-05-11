package lv.java2.shopping_list.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(schema = "java2", name = "shopping_list_items")
public class ShoppingListItem {

    private static final BigDecimal DEFAULT_PRICE = BigDecimal.valueOf(0);
    private static final Integer DEFAULT_QUANTITY = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "list_id", nullable = false)
    private ShoppingList shoppingList;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private BigDecimal price;

    public ShoppingListItem() {
    }

    public ShoppingListItem(ShoppingList shoppingList, Item item) {
        this.shoppingList = shoppingList;
        this.item = item;
        this.quantity = DEFAULT_QUANTITY;
        this.price = DEFAULT_PRICE;
    }

    public ShoppingListItem(ShoppingList shoppingList, Item item, Integer quantity) {
        this.shoppingList = shoppingList;
        this.item = item;
        this.quantity = quantity;
    }

    public ShoppingListItem(Long id, Item item, ShoppingList shoppingList, Integer quantity, BigDecimal price) {
        this.id = id;
        this.item = item;
        this.shoppingList = shoppingList;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingListItem item1 = (ShoppingListItem) o;
        return Objects.equals(id, item1.id) &&
                Objects.equals(item, item1.item) &&
                Objects.equals(shoppingList, item1.shoppingList) &&
                Objects.equals(quantity, item1.quantity) &&
                Objects.equals(price, item1.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item, shoppingList, quantity, price);
    }
}

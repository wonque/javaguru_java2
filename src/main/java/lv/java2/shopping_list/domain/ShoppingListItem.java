package lv.java2.shopping_list.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(schema = "java2", name = "shopping_list_items")
public class ShoppingListItem {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "LIST_ID", nullable = false)
    private ShoppingList shoppingList;
    @ManyToOne
    @JoinColumn(name = "ITEM_ID", nullable = false)
    private Item item;
    @Column(name = "quantity")
    private Double quantity;
    @Column(name = "price")
    private BigDecimal price;

}

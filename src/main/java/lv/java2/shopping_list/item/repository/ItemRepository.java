package lv.java2.shopping_list.item.repository;

import lv.java2.shopping_list.item.domain.Item;
import lv.java2.shopping_list.shoppinglist.domain.ShoppingList;

import java.math.BigDecimal;
import java.util.Optional;

public interface ItemRepository {

    Item addItemToShoppingList(Item item);

    Optional<Item> findItemByTitle(ShoppingList shoppingList, String itemTitle);

    int removeAllItems (ShoppingList shoppingList);

    boolean removeSingleItem(Item item);

    boolean updateDescription(Item item, String description);

    boolean updatePrice (Item item, BigDecimal price);

}

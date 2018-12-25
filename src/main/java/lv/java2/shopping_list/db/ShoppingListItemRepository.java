package lv.java2.shopping_list.db;

import lv.java2.shopping_list.domain.shoppinglist.ShoppingList;
import lv.java2.shopping_list.domain.item.ShoppingListItem;

import java.math.BigDecimal;
import java.util.Optional;

public interface ShoppingListItemRepository {

    ShoppingListItem addItemToShoppingList(ShoppingListItem item);

    Optional<ShoppingListItem> findItemByTitle(ShoppingList shoppingList, String itemTitle);

    int removeAllItems (ShoppingList shoppingList);

    boolean removeSingleItem(ShoppingListItem item);

    boolean updateDescription(ShoppingListItem item, String description);

    boolean updatePrice (ShoppingListItem item, BigDecimal price);

}

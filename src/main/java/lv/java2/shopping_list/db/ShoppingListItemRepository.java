package lv.java2.shopping_list.db;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.domain.ShoppingListItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ShoppingListItemRepository {

    ShoppingListItem addItemToShoppingList(ShoppingListItem item);

    Optional<ShoppingListItem> findItemByTitle(ShoppingList shoppingList, String itemTitle);

    int removeAllItemsFromShoppingList (ShoppingList shoppingList);

    boolean removeSingleItem(ShoppingListItem item);

    int updateDescription(ShoppingListItem item, String description);

    int updatePrice (ShoppingListItem item, BigDecimal price);


}

package lv.java2.shopping_list.item.repository;

import lv.java2.shopping_list.item.domain.Item;
import lv.java2.shopping_list.shoppinglist.domain.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

//    Item addItemToShoppingList(Item item);
//
    @Query("FROM Item sl WHERE sl.shoppingList = :shoppingList AND lower(sl.title) = :title")
    Optional<Item> findItemByTitle(ShoppingList shoppingList, String title);
//
    @Query("DELETE FROM Item sl WHERE shoppingList = :shoppingList")
    int removeAllItems (ShoppingList shoppingList);
//
//    boolean removeSingleItem(Item item);
//
//    boolean updateDescription(Item item, String description);
//
//    boolean updatePrice (Item item, BigDecimal price);

}

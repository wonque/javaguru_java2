package lv.java2.shopping_list.db.orm;

import lv.java2.shopping_list.db.ShoppingListItemRepository;
import lv.java2.shopping_list.domain.shoppinglist.ShoppingList;
import lv.java2.shopping_list.domain.item.ShoppingListItem;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Repository
@Transactional
public class ShoppingListItemRepositoryImpl extends ORMRepository implements ShoppingListItemRepository {

    @Override
    public ShoppingListItem addItemToShoppingList(ShoppingListItem item) {
        session().save(item);
        return item;
    }

    @Override
    public Optional<ShoppingListItem> findItemByTitle(ShoppingList shoppingList, String itemTitle) {
        String query = "FROM ShoppingListItem sl WHERE sl.shoppingList = :shoppingList AND lower(sl.title) = :title";
        ShoppingListItem shoppingListItem = (ShoppingListItem) session().createQuery(query)
                .setParameter("shoppingList", shoppingList)
                .setParameter("title", itemTitle.toLowerCase()).uniqueResult();
        return Optional.ofNullable(shoppingListItem);
    }

    @Override
    public int removeAllItems(ShoppingList shoppingList) {
        String query = "DELETE FROM ShoppingListItem sl WHERE shoppingList = :shoppingList";
        return session().createQuery(query)
                .setParameter("shoppingList", shoppingList)
                .executeUpdate();
    }

    @Override
    public boolean removeSingleItem(ShoppingListItem item) {
        session().remove(item);
        return true;
    }

    @Override
    public boolean updateDescription(ShoppingListItem item, String description) {
        String query = "UPDATE ShoppingListItem sl SET sl.description = :description WHERE sl.itemId = :itemId";
        int itemsUpdated = session().createQuery(query)
                .setParameter("description", description)
                .setParameter("itemId", item.getItemId())
                .executeUpdate();
        return itemsUpdated > 0;
    }

    @Override
    public boolean updatePrice(ShoppingListItem item, BigDecimal price) {
        String query = "UPDATE ShoppingListItem sl SET sl.price = :price WHERE sl.itemId = :itemId";
        int itemsUpdated = session().createQuery(query)
                .setParameter("price", price)
                .setParameter("itemId", item.getItemId())
                .executeUpdate();
        return itemsUpdated > 0;
    }

}




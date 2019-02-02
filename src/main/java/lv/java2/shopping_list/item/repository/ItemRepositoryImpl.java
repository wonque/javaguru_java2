//package lv.java2.shopping_list.item.repository;
//
//import lv.java2.shopping_list.db.orm.ORMRepository;
//import lv.java2.shopping_list.shoppinglist.domain.ShoppingList;
//import lv.java2.shopping_list.item.domain.Item;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.math.BigDecimal;
//import java.util.Optional;
//
//@Repository
//@Transactional
//public class ItemRepositoryImpl extends ORMRepository implements ItemRepository {
//
//    @Override
//    public Item addItemToShoppingList(Item item) {
//        session().save(item);
//        return item;
//    }
//
//    @Override
//    public Optional<Item> findItemByTitle(ShoppingList shoppingList, String itemTitle) {
//        String query = "FROM Item sl WHERE sl.shoppingList = :shoppingList AND lower(sl.title) = :title";
//        Item item = (Item) session().createQuery(query)
//                .setParameter("shoppingList", shoppingList)
//                .setParameter("title", itemTitle.toLowerCase()).uniqueResult();
//        return Optional.ofNullable(item);
//    }
//
//    @Override
//    public int removeAllItems(ShoppingList shoppingList) {
//        String query = "DELETE FROM Item sl WHERE shoppingList = :shoppingList";
//        return session().createQuery(query)
//                .setParameter("shoppingList", shoppingList)
//                .executeUpdate();
//    }
//
//    @Override
//    public boolean removeSingleItem(Item item) {
//        session().remove(item);
//        return true;
//    }
//
//    @Override
//    public boolean updateDescription(Item item, String description) {
//        String query = "UPDATE Item sl SET sl.description = :description WHERE sl.itemId = :itemId";
//        int itemsUpdated = session().createQuery(query)
//                .setParameter("description", description)
//                .setParameter("itemId", item.getItemId())
//                .executeUpdate();
//        return itemsUpdated > 0;
//    }
//
//    @Override
//    public boolean updatePrice(Item item, BigDecimal price) {
//        String query = "UPDATE Item sl SET sl.price = :price WHERE sl.itemId = :itemId";
//        int itemsUpdated = session().createQuery(query)
//                .setParameter("price", price)
//                .setParameter("itemId", item.getItemId())
//                .executeUpdate();
//        return itemsUpdated > 0;
//    }
//
//}
//
//
//

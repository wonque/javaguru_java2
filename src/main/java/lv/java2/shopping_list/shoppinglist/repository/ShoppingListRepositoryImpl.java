//package lv.java2.shopping_list.shoppinglist.repository;
//
//import lv.java2.shopping_list.shoping_list.db.orm.ORMRepository;
//import lv.java2.shopping_list.account.domain.User;
//import lv.java2.shopping_list.shoppinglist.domain.ShoppingList;
//import org.springframework.lang.Nullable;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//@Transactional
//public class ShoppingListRepositoryImpl extends ORMRepository implements ShoppingListRepository {
//
//
//    @Override
//    public ShoppingList addToDataBase(ShoppingList shoppingList) {
//        session().save(shoppingList);
//        return shoppingList;
//    }
//
//    @Override
//    @Nullable
//    public Optional<ShoppingList> findById(Long accountId, Long listId) {
//        String query = "FROM ShoppingList sl WHERE sl.account.id = :accountId AND id = :listId";
//        ShoppingList shoppingList = (ShoppingList) session().createQuery(query)
//                .setParameter("accountId", accountId)
//                .setParameter("listId", listId)
//                .uniqueResult();
//        return Optional.ofNullable(shoppingList);
//    }
//
//    @Override
//    @Nullable
//    public Optional<ShoppingList> getByUserAndTitle(User account, String title) {
//        String query = "FROM ShoppingList sl WHERE account = :account AND lower(title) = :title";
//        ShoppingList shoppingList = (ShoppingList) session().createQuery(query)
//                .setParameter("account", account)
//                .setParameter("title", title.toLowerCase()).uniqueResult();
//        return Optional.ofNullable(shoppingList);
//    }
//
//    @Override
//    public boolean remove(ShoppingList shoppingList) {
//
//        session().remove(shoppingList);
//        return true;
//    }
//
//    @Override
//    public List<ShoppingList> findAllListsByAccountId(Long accountId) {
//        String query = "FROM ShoppingList sl WHERE sl.account.id = :id";
//        return session().createQuery(query, ShoppingList.class)
//                .setParameter("id", accountId).getResultList();
//    }
//
//    @Override
//    @Nullable
//    public Optional<ShoppingList> findByAccountIdAndTitle(Long accountId, String title) {
//        String query = "FROM ShoppingList sl WHERE account.id = :accountId AND lower(title) = :title";
//        ShoppingList shoppingList = (ShoppingList) session().createQuery(query)
//                .setParameter("accountId", accountId)
//                .setParameter("title", title.toLowerCase()).uniqueResult();
//        return Optional.ofNullable(shoppingList);
//    }
//
//}
//
//

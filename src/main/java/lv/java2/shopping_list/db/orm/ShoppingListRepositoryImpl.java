package lv.java2.shopping_list.db.orm;

import lv.java2.shopping_list.db.ShoppingListRepository;
import lv.java2.shopping_list.domain.account.Account;
import lv.java2.shopping_list.domain.shoppinglist.ShoppingList;
import lv.java2.shopping_list.domain.item.ShoppingListItem;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ShoppingListRepositoryImpl extends ORMRepository implements ShoppingListRepository {


    @Override
    public ShoppingList addToDataBase(ShoppingList shoppingList) {
        session().save(shoppingList);
        return shoppingList;
    }

    @Override
    public Optional<ShoppingList> findById(Long id) {
        String query = "FROM ShoppingList sl WHERE id = :id";
        ShoppingList shoppingList = (ShoppingList) session().createQuery(query)
                .setParameter("id", id).uniqueResult();
        return Optional.of(shoppingList);
    }

    @Override
    @Nullable
    public Optional<ShoppingList> findByAccountAndTitle(Account account, String title) {
        String query = "FROM ShoppingList sl WHERE account = :account AND lower(title) = :title";
        ShoppingList shoppingList = (ShoppingList) session().createQuery(query)
                .setParameter("account", account)
                .setParameter("title", title.toLowerCase()).uniqueResult();
        return Optional.ofNullable(shoppingList);
    }

    @Override
    public boolean remove(ShoppingList shoppingList) {
        session().remove(shoppingList);
        return true;
    }

//    @Override
//    public List<ShoppingListItem> getShoppingList(ShoppingList shoppingList) {
//        String query = "FROM ShoppingListItem sl WHERE shoppingList = :shoppingList";
//        return session().createQuery(query, ShoppingListItem.class)
//                .setParameter("shoppingList", shoppingList).list();
//    }

    @Override
    public List<ShoppingList> findAllLists(Account account) {
        String query = "FROM ShoppingList sl WHERE sl.account = :account";
        return session().createQuery(query, ShoppingList.class)
                .setParameter("account", account).getResultList();
    }

}



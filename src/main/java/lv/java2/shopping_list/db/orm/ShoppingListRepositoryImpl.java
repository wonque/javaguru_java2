package lv.java2.shopping_list.db.orm;

import lv.java2.shopping_list.db.ShoppingListRepository;
import lv.java2.shopping_list.domain.Account;
import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.domain.ShoppingListItem;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class ShoppingListRepositoryImpl extends ORMRepository implements ShoppingListRepository {


    @Override
    public ShoppingList addToDataBase(ShoppingList shoppingList) {
        session().save(shoppingList);
        return shoppingList;
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

    @Override
    public List<ShoppingListItem> findAllItems(ShoppingList shoppingList) {
        String query = "FROM ShoppingListItem sl WHERE shoppingList = :shoppingList";
        return session().createQuery(query, ShoppingListItem.class)
                .setParameter("shoppingList", shoppingList).list();
    }
}

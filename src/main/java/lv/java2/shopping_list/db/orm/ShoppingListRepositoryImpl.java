package lv.java2.shopping_list.db.orm;

import lv.java2.shopping_list.db.ShoppingListRepository;
import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.domain.ShoppingListItem;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    public Optional<ShoppingList> getByTitle(String title) {
        String query = "FROM ShoppingList WHERE lower(title) = :title";
        ShoppingList shoppingList = (ShoppingList) session().createQuery(query)
                .setParameter("title", title.toLowerCase()).uniqueResult();
        ;
        return Optional.ofNullable(shoppingList);
    }

    @Override
    public boolean remove(ShoppingList shoppingList) {
        session().remove(shoppingList);
        return true;
    }

    @Override
    @Nullable
    public Optional<ShoppingList> getById(Long id) {
        String query = "FROM ShoppingList WHERE lower(title) = :title";
        ShoppingList shoppingList = (ShoppingList) session().createQuery(query)
                .setParameter("id", id).uniqueResult();
        ;
        return Optional.ofNullable(shoppingList);
    }

    @Override
    public boolean removeListItems(ShoppingList shoppingList) {
        session().remove(shoppingList);
        return true;
    }
}

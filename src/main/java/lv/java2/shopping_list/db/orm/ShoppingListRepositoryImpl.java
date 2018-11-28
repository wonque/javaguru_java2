package lv.java2.shopping_list.db.orm;

import lv.java2.shopping_list.db.ShoppingListRepository;
import lv.java2.shopping_list.domain.ShoppingList;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class ShoppingListRepositoryImpl implements ShoppingListRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public ShoppingList addToDataBase(ShoppingList shoppingList) {
        sessionFactory.getCurrentSession().save(shoppingList);
        return shoppingList;
    }

    @Override
    @Nullable
    public Optional<ShoppingList> getByTitle(String title) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM ShoppingList WHERE lower(title) = :title");
        query.setParameter("title", title.toLowerCase());
        ShoppingList shoppingList = (ShoppingList) query.uniqueResult();
        return Optional.ofNullable(shoppingList);
    }

    @Override
    public boolean remove(ShoppingList shoppingList) {
        return false;
    }


}

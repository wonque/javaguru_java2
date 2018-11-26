package lv.java2.shopping_list.db.orm;

import lv.java2.shopping_list.db.ShoppingListRepository;
import lv.java2.shopping_list.domain.ShoppingList;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Transactional
public class ShoppingListRepositoryImpl implements ShoppingListRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public ShoppingList addToDataBase(ShoppingList shoppingList) {
        sessionFactory.getCurrentSession().save(shoppingList);
        return shoppingList;
    }

    @Override
    public Optional<ShoppingList> getByTitle(String title) {
        return Optional.empty();
    }

    @Override
    public boolean isInDataBase(String title) {
        return false;
    }
}

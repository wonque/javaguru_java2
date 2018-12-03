package lv.java2.shopping_list.db.orm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class ORMRepository {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session session() {
        return sessionFactory.getCurrentSession();
    }


}

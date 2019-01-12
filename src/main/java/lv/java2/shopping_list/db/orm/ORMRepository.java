package lv.java2.shopping_list.db.orm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;

@Component
public abstract class ORMRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    protected Session session() {
        return entityManagerFactory.unwrap(SessionFactory.class).openSession();
    }


}

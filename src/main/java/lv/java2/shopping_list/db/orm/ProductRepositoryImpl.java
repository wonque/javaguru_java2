package lv.java2.shopping_list.db.orm;

import lv.java2.shopping_list.db.ProductRepository;
import lv.java2.shopping_list.domain.Product;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Product addToDataBase(Product product) {
        sessionFactory.getCurrentSession().save(product);
        return product;
    }

    @Override
    @Nullable
    public Optional<Product> findByTitle(String title) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Product WHERE lower(title) = :title");
        query.setParameter("title", title.toLowerCase());
        Product product = (Product) query.uniqueResult();
        return Optional.ofNullable(product);
    }

    @Override
    public boolean remove(Product product) {
        sessionFactory.getCurrentSession().remove(product);
        return true;
    }

    @Override
    public List<Product> getProductList() {
        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        query.from(Product.class);
        return sessionFactory.getCurrentSession().createQuery(query).getResultList();
    }

}

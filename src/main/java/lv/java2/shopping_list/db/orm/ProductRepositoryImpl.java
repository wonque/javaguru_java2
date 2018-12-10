package lv.java2.shopping_list.db.orm;

import lv.java2.shopping_list.db.ProductRepository;
import lv.java2.shopping_list.domain.Product;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class ProductRepositoryImpl extends ORMRepository implements ProductRepository {


    @Override
    public Product addToDataBase(Product product) {
        session().save(product);
        return product;
    }

    @Override
    @Nullable
    public Optional<Product> findByTitle(String title) {
        String query = "FROM Product WHERE title = :title";
        Product product = (Product) session().createQuery(query)
                .setParameter("title", title.toLowerCase()).uniqueResult();
        return Optional.ofNullable(product);
    }

    @Override
    @Nullable
    public Optional<Product> findById(Long id) {
        String query = "FROM Product WHERE id = :id";
        Product product = (Product) session().createQuery(query)
                .setParameter("id", id).uniqueResult();
        return Optional.ofNullable(product);
    }

    @Override
    public boolean remove(Product product) {
        session().remove(product);
        return true;
    }

    @Override
    public List<Product> getProductList() {
        CriteriaBuilder builder = session().getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        query.from(Product.class);
        return session().createQuery(query).getResultList();
    }
}

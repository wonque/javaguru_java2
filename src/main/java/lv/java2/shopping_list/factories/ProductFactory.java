package lv.java2.shopping_list.factories;

import lv.java2.shopping_list.db.ProductRepository;
import lv.java2.shopping_list.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductFactory {

    @Autowired
    private ProductRepository repository;

    public Product saveProductToBase(String title) {
        Product newEntry = createNewProduct(title);
        return repository.addToDataBase(newEntry);

    }

    private Product createNewProduct(String title) {
        return new Product(title);
    }
}


//TODO Add repository and save() to db method
//TODO all manipulates with class Product - inside Factory
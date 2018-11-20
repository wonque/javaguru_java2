package services.add;

import db.jdbc.ProductRepositoryImpl;
import domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.Error;
import services.add.validation.AddProductValidator;

import java.util.List;

@Component
public class AddProductService {

    @Autowired
    private ProductRepositoryImpl productRepository;

    @Autowired
    AddProductValidator validator;


    public AddProductResponse add(AddProductRequest request) {
        List<Error> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddProductResponse(errors);
        }

        Product product = createNewProduct(request.getTitle());

        productRepository.addToDataBase(product);

        return new AddProductResponse(product.getId());
    }


    private Product createNewProduct(String title) {
        return new Product(title);
    }

}

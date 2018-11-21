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
    private AddProductValidator validator;


    public AddProductResponse add(AddProductRequest request) {
        List<Error> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddProductResponse(errors);
        }

        Product newEntry = new Product((request.getTitle()));

        productRepository.addToDataBase(newEntry);

        return new AddProductResponse(newEntry.getId());
    }
}

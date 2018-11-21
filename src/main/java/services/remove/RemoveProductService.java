package services.remove;

import db.jdbc.ProductRepositoryImpl;
import domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.Error;

import java.util.List;
import java.util.Optional;

@Component
public class RemoveProductService {

    @Autowired
    private ProductRepositoryImpl productRepository;

    @Autowired
    private RemoveProductValidator validator;


    public RemoveProductResponse remove(RemoveProductRequest request) {
        List<Error> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new RemoveProductResponse(errors);
        }
        Product productToDelete = getProductToDelete(request);
        return new RemoveProductResponse(productRepository.remove(productToDelete));
    }

    private Product getProductToDelete(RemoveProductRequest request) {
        Product productToDelete = null;
        Optional<Product> foundedProduct = productRepository.findByTitle(request.getTitle());
        if (foundedProduct.isPresent()) {
            productToDelete = foundedProduct.get();
        }
        return productToDelete;
    }
}

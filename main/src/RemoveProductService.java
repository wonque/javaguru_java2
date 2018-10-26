import java.util.List;
import java.util.Optional;

public class RemoveProductService {

    Database database;

    public RemoveProductService (Database database){
        this.database = database;
    }

    public boolean remove(String title) {
        Optional<Product> foundedProduct = database.findProductByTitle(title);
        if (foundedProduct.isPresent()) {
            Product productToDelete = foundedProduct.get();
            return database.remove(productToDelete);
        }
        return false;
    }
}

package db;

import domain.Product;
import java.math.*;

public interface ProductDetailsUpdate {

    void updateDescription(Product product, String description);

    void updatePrice(Product product, BigDecimal price);
}

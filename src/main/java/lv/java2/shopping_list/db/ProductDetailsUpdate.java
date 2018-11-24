package lv.java2.shopping_list.db;

import lv.java2.shopping_list.domain.Product;
import java.math.*;

public interface ProductDetailsUpdate {

    void updateDescription(Product product, String description);

    void updatePrice(Product product, BigDecimal price);
}

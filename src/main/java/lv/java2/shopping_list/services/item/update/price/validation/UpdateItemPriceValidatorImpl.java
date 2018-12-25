package lv.java2.shopping_list.services.item.update.price.validation;

import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.item.ItemUpdateSharedRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UpdateItemPriceValidatorImpl implements UpdateItemPriceValidator {

    @Override
    public Optional<ShoppingListError> validate(ItemUpdateSharedRequest request) {
        BigDecimal price = request.getPrice();
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            ShoppingListError error = new ShoppingListError("price", "Price cannot be negative!");
            return Optional.of(error);
        }
        return Optional.empty();
    }
}
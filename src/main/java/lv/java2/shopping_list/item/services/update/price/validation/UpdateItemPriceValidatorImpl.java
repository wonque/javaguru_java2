package lv.java2.shopping_list.item.services.update.price.validation;

import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.item.services.ItemUpdateSharedRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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
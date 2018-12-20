package lv.java2.shopping_list.services.item.update.price.validation;

import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.item.ItemUpdateSharedRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UpdateItemPriceValidatorImpl implements UpdateItemPriceValidator {


    @Override
    public List<ShoppingListError> validate(ItemUpdateSharedRequest priceRequest) {
        List<ShoppingListError> errors = new ArrayList<>();
        validateNegativePrice(priceRequest.getPrice()).ifPresent(errors::add);
        return errors;
    }

    private Optional<ShoppingListError> validateNegativePrice(double price) {
        if (price < 0) {
            ShoppingListError error = new ShoppingListError("price", "Price cannot be negative!");
            return Optional.of(error);
        }
        return Optional.empty();
    }
}
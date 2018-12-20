package lv.java2.shopping_list.services.item.update.price;

import lv.java2.shopping_list.db.ShoppingListItemRepository;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.item.ItemUpdateSharedRequest;
import lv.java2.shopping_list.services.item.update.ItemUpdateSharedResponse;
import lv.java2.shopping_list.services.item.update.price.validation.UpdateItemPriceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class UpdateItemPriceService {

    @Autowired
    private UpdateItemPriceValidator validator;
    @Autowired
    private ShoppingListItemRepository itemRepository;

    public ItemUpdateSharedResponse updatePrice(ItemUpdateSharedRequest priceRequest) {
        List<ShoppingListError> errors = validator.validate(priceRequest);
        if (errors.isEmpty()) {
            BigDecimal convertedPrice = convertToBigDecimal(priceRequest.getPrice());
            int itemsUpdated = itemRepository.updatePrice(priceRequest.getShoppingListItm(), convertedPrice);
            if (itemsUpdated > 0) {
                return new ItemUpdateSharedResponse(true);
            } else {
                errors.add(new ShoppingListError("price", "Unable to update price!"));
            }
        }
        return new ItemUpdateSharedResponse(errors);

    }

    private BigDecimal convertToBigDecimal(double price) {
        return new BigDecimal(price);
    }
}

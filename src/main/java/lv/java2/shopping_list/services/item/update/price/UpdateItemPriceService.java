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
import java.util.Optional;

@Component
public class UpdateItemPriceService {

    @Autowired
    private UpdateItemPriceValidator validator;
    @Autowired
    private ShoppingListItemRepository itemRepository;

    public ItemUpdateSharedResponse updatePrice(ItemUpdateSharedRequest priceRequest) {
        Optional<ShoppingListError> error = validator.validate(priceRequest);
        if (error.isPresent()) {
            return new ItemUpdateSharedResponse(error.get());
        }else {
            boolean updated = itemRepository.updatePrice(priceRequest.getShoppingListItm(),
                    priceRequest.getPrice());
            return generatePriceUpdateResponse(updated);
        }
    }

    private ItemUpdateSharedResponse generatePriceUpdateResponse(boolean isUpdated) {
        if (isUpdated) {
            return new ItemUpdateSharedResponse(isUpdated);
        } else {
            ShoppingListError error = new ShoppingListError("price", "Unable to update price!");
            return new ItemUpdateSharedResponse(error);
        }
    }
}

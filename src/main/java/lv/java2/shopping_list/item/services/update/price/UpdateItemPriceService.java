package lv.java2.shopping_list.item.services.update.price;

import lv.java2.shopping_list.item.repository.ItemRepository;
import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.item.services.ItemUpdateSharedRequest;
import lv.java2.shopping_list.item.services.update.ItemUpdateSharedResponse;
import lv.java2.shopping_list.item.services.update.price.validation.UpdateItemPriceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UpdateItemPriceService {

    @Autowired
    private UpdateItemPriceValidator validator;
    @Autowired
    private ItemRepository itemRepository;

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

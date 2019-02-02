package lv.java2.shopping_list.item.services.addition;

import lv.java2.shopping_list.item.repository.ItemRepository;
import lv.java2.shopping_list.item.domain.Item;
import lv.java2.shopping_list.item.domain.ItemFactory;
import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.item.services.addition.validation.ItemAdditionValidator;
import lv.java2.shopping_list.item.services.ItemAddRemoveSharedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemAdditionService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemAdditionValidator validator;

    @Autowired
    private ItemFactory itemBuilder;

    @Transactional
    public ItemAdditionResponse addItem(ItemAddRemoveSharedRequest request) {
        List<ShoppingListError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ItemAdditionResponse(errors);
        }
        Item newEntry = itemBuilder.createWithTitle(request.getShoppingList(), request.getTitle());
        itemRepository.save(newEntry);
        return new ItemAdditionResponse(newEntry);
    }
}

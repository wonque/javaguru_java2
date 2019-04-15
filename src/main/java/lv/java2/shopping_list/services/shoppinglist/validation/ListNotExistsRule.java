package lv.java2.shopping_list.services.shoppinglist.validation;

import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.web.dto.ShoppingListDTO;
import lv.java2.shopping_list.web.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListNotExistsRule implements ShoppingListValidationRule {

    private ShoppingListRepository repository;

    @Autowired
    public ListNotExistsRule(ShoppingListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validate(ShoppingListDTO dto) {
        if (!repository.findByUserIdAndListId(dto.getUserId(), dto.getId()).isPresent()) {
            throw new ResourceNotFoundException("Shopping list not found!");
        }
    }
}

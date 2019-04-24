package lv.java2.shopping_list.services.shoppinglist.validation;

import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.web.dto.ShoppingListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class ListNotExistsRule implements ShoppingListValidationRule {

    private ShoppingListRepository repository;

    @Autowired
    public ListNotExistsRule(ShoppingListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validate(ShoppingListDTO dto) {
        if (dto.getId() != null && listNotPresent(dto)) {
            throw new EntityNotFoundException("Shopping list not found!");
        }
    }

    private boolean listNotPresent(ShoppingListDTO dto) {
        return !repository.findByUserIdAndListId(dto.getUserId(), dto.getId()).isPresent();
    }

}

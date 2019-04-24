package lv.java2.shopping_list.services.item.validation;

import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.web.dto.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class ListIDNotExistsRule implements ItemValidationRule {

    private ShoppingListRepository repository;

    @Autowired
    private ListIDNotExistsRule(ShoppingListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validate(ItemDTO dto) {
        if (dto.getListId() == null || listIdNotExists(dto)) {
            throw new EntityNotFoundException("Shopping list not found");
        }
    }

    private boolean listIdNotExists(ItemDTO dto) {
        return !repository.existsById(dto.getListId());
    }
}

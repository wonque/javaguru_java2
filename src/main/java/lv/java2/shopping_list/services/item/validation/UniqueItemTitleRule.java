package lv.java2.shopping_list.services.item.validation;

import lv.java2.shopping_list.repository.ItemRepository;
import lv.java2.shopping_list.web.dto.ItemDTO;
import lv.java2.shopping_list.web.exceptions.DuplicateResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueItemTitleRule implements ItemValidationRule {

    private ItemRepository repository;

    @Autowired
    public UniqueItemTitleRule(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validate(ItemDTO dto) {
        if (repository.findByTitle(dto.getTitle()).isPresent()) {
            String message = String.format("Item with title = '%s' already exists!", dto.getTitle());
            throw new DuplicateResourceException(message);
        }

    }
}

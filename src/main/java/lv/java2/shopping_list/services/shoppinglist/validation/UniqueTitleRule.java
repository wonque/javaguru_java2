package lv.java2.shopping_list.services.shoppinglist.validation;

import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.web.dto.ShoppingListDTO;
import lv.java2.shopping_list.web.exceptions.DuplicateResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueTitleRule implements ShoppingListValidationRule {

    private ShoppingListRepository repository;

    @Autowired
    public UniqueTitleRule(ShoppingListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validate(ShoppingListDTO dto) {
        if (repository.findByUserIdAndTitle(dto.getUserId(), dto.getTitle()).isPresent()) {
            throw new DuplicateResourceException(
                    String.format("Shopping list with title = '%s' already exists!", dto.getTitle()));
        }
    }
}

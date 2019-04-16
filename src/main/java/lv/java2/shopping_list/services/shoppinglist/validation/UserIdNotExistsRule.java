package lv.java2.shopping_list.services.shoppinglist.validation;

import lv.java2.shopping_list.repository.UserRepository;
import lv.java2.shopping_list.web.dto.ShoppingListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class UserIdNotExistsRule implements ShoppingListValidationRule {

    private UserRepository repository;

    @Autowired
    public UserIdNotExistsRule(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validate(ShoppingListDTO dto) {
        if (dto.getUserId() == null || !repository.findById(dto.getUserId()).isPresent()) {
            throw new EntityNotFoundException("User with ID = " + dto.getUserId() + " not found!");
        }
    }
}

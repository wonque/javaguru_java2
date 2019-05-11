package lv.java2.shopping_list.services.shoppinglist.validation;

import lv.java2.shopping_list.dto.ShoppingListDTO;
import lv.java2.shopping_list.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class UserNotExistsRule implements ShoppingListValidationRule {

    private UserRepository userRepository;

    @Autowired
    private UserNotExistsRule(UserRepository repository) {
        this.userRepository = repository;
    }

    @Override
    public void validate(ShoppingListDTO dto) {
        if (!userRepository.existsById(dto.getUserId())) {
            String message = String.format("User with id = %s not found!", dto.getUserId());
            throw new EntityNotFoundException(message);
        }

    }
}

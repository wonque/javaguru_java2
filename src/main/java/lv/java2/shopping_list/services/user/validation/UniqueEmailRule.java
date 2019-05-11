package lv.java2.shopping_list.services.user.validation;

import lv.java2.shopping_list.dto.UserDTO;
import lv.java2.shopping_list.exceptions.DuplicateResourceException;
import lv.java2.shopping_list.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueEmailRule implements UserValidationRule {

    private final UserRepository repository;

    @Autowired
    public UniqueEmailRule(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validate(UserDTO userDTO) {
        if (repository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new DuplicateResourceException("User with " + userDTO.getEmail() + " already registered!");
        }

    }
}

package lv.java2.shopping_list.services.user.validation;

import lv.java2.shopping_list.web.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserValidationService {

    private Set<UserValidationRule> validationRules;

    public UserValidationService(Set<UserValidationRule> validationRules) {
        this.validationRules = validationRules;
    }

    public void validate(UserDTO userDTO) {
        validationRules.forEach(rule -> rule.validate(userDTO));
    }
}

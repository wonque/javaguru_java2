package lv.java2.shopping_list.services.shoppinglist.validation;

import lv.java2.shopping_list.dto.ShoppingListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ShoppingListValidationService {

    private Set<ShoppingListValidationRule> validationRules;

    @Autowired
    public ShoppingListValidationService(Set<ShoppingListValidationRule> validationRules) {
        this.validationRules = validationRules;
    }

    public void validate(ShoppingListDTO listDTO) {
        validationRules.forEach(rule -> rule.validate(listDTO));

    }
}

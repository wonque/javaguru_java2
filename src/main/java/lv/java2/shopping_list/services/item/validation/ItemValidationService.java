package lv.java2.shopping_list.services.item.validation;

import lv.java2.shopping_list.web.dto.ItemDTO;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ItemValidationService {

    private Set<ItemValidationRule> rules;

    public ItemValidationService(Set<ItemValidationRule> rules) {
        this.rules = rules;
    }

    public void validate(ItemDTO itemDTO) {
        rules.forEach(rule -> rule.validate(itemDTO));
    }
}

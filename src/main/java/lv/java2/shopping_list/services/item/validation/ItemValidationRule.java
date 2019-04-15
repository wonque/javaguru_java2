package lv.java2.shopping_list.services.item.validation;

import lv.java2.shopping_list.web.dto.ItemDTO;

public interface ItemValidationRule {

    void validate(ItemDTO dto);
}

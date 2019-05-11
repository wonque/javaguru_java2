package lv.java2.shopping_list.services.user.validation;

import lv.java2.shopping_list.dto.UserDTO;

public interface UserValidationRule {

    void validate(UserDTO userDTO);
}

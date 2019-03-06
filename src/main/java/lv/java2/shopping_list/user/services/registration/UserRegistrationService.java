package lv.java2.shopping_list.user.services.registration;

import lv.java2.shopping_list.ServiceResponse;
import lv.java2.shopping_list.web.dto.UserDTO;

public interface UserRegistrationService {

    ServiceResponse<UserDTO> register(UserDTO userDTO);

}

package lv.java2.shopping_list.web.dto.mappers;

import lv.java2.shopping_list.user.domain.User;
import lv.java2.shopping_list.web.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper (componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "userId", source = "id")
    UserDTO toDTO(User user);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "id", source = "userId")
    @Mapping(target = "dateCreated", ignore = true)
    User toDomain (UserDTO userDTO);

}

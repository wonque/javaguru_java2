package lv.java2.shopping_list.web.dto.mappers;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.web.dto.ShoppingListDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ShoppingListMapper {


    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "status", source = "status")
    ShoppingListDTO toDTO(ShoppingList shoppingList);

    @Mapping(target = "user.id", source = "userId")
    ShoppingList toDomain(ShoppingListDTO dto);

}

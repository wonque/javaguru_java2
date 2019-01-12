package lv.java2.shopping_list.web.dtos.mappers;

import lv.java2.shopping_list.domain.shoppinglist.ShoppingList;
import lv.java2.shopping_list.web.dtos.ShoppingListDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ShoppingListMapper {


    ShoppingList dtoToDomain(ShoppingListDTO shoppingListDTO);

    @Mappings({
            @Mapping(target = "account", ignore = true),
            @Mapping(target = "ownedBy", source = "account.login")
    })
    ShoppingListDTO domainToDto(ShoppingList shoppingList);
}

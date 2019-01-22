package lv.java2.shopping_list.shoppinglist.web;

import lv.java2.shopping_list.shoppinglist.domain.ShoppingList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ShoppingListMapper {

    @Mappings({
            @Mapping(target = "account.id", source = "accountId")
    })
    ShoppingList dtoToDomain(ShoppingListDTO shoppingListDTO);

    @Mappings({
            @Mapping(target = "accountId", source = "account.id"),
            @Mapping(target = "ownedBy", source = "account.login")
    })
    ShoppingListDTO domainToDto(ShoppingList shoppingList);
}

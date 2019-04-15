package lv.java2.shopping_list.web.dto.mappers;

import lv.java2.shopping_list.domain.Item;
import lv.java2.shopping_list.web.dto.ItemDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ItemMapper {

    Item toDomain(ItemDTO dto);

    ItemDTO toDTO(Item item);
}

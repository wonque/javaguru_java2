package lv.java2.shopping_list.services.shoppinglist.addition;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.domain.ShoppingListStatus;
import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.services.shoppinglist.ShoppingListDBValidator;
import lv.java2.shopping_list.web.dto.ShoppingListDTO;
import lv.java2.shopping_list.web.dto.mappers.ShoppingListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShoppingListAdditionService {

    private final ShoppingListRepository repository;
    private final ShoppingListMapper mapper;
    private final ShoppingListDBValidator validator;

    @Autowired
    public ShoppingListAdditionService(ShoppingListRepository repository, ShoppingListMapper mapper,
                                       ShoppingListDBValidator validator) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Transactional
    public ShoppingListDTO addList(ShoppingListDTO shoppingListDTO) {
        validator.isListTitleExists(shoppingListDTO.getUserId(), shoppingListDTO.getTitle());
        ShoppingList newEntry = mapper.toDomain(shoppingListDTO);
        newEntry.setStatus(ShoppingListStatus.ACTIVE);
        repository.save(newEntry);
        shoppingListDTO.setDateCreated(newEntry.getDateCreated());
        shoppingListDTO.setId(newEntry.getId());
        shoppingListDTO.setStatus(newEntry.getStatus());
        return shoppingListDTO;
    }

}

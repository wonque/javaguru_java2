package lv.java2.shopping_list.services.shoppinglist.addition;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.domain.ShoppingListStatus;
import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.services.shoppinglist.ShoppingListDBValidator;
import lv.java2.shopping_list.web.dto.ShoppingListDTO;
import lv.java2.shopping_list.web.dto.mappers.ShoppingListMapper;
import lv.java2.shopping_list.web.exceptions.DuplicateResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShoppingListAdditionService {

    @Autowired
    private ShoppingListRepository repository;
    @Autowired
    private ShoppingListMapper mapper;
    @Autowired
    private ShoppingListDBValidator validator;

    @Transactional
    public ShoppingListDTO addList(ShoppingListDTO shoppingListDTO) {
        if (validator.isShoppingListTitleDuplicate(shoppingListDTO.getUserId(), shoppingListDTO.getTitle())) {
            String message = String.format("Shopping list with title = '%s' already exists!", shoppingListDTO.getTitle());
            throw new DuplicateResourceException(message);
        }
        ShoppingList newEntry = mapper.toDomain(shoppingListDTO);
        newEntry.setStatus(ShoppingListStatus.ACTIVE);
        repository.save(newEntry);
        shoppingListDTO.setDateCreated(newEntry.getDateCreated());
        shoppingListDTO.setId(newEntry.getId());
        shoppingListDTO.setStatus(newEntry.getStatus());
        return shoppingListDTO;
    }

}

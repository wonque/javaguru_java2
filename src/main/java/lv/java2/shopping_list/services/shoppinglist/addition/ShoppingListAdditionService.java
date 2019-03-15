package lv.java2.shopping_list.services.shoppinglist.addition;

import lv.java2.shopping_list.ServiceResponse;
import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.domain.User;
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
    public ServiceResponse<ShoppingListDTO> addList(ShoppingListDTO shoppingListDTO) {
        ShoppingList newEntry = mapper.toDomain(shoppingListDTO);
        validateDuplicateTitle(newEntry.getUser(), shoppingListDTO.getTitle());
        newEntry.activateShoppingList();
        repository.save(newEntry);
        shoppingListDTO = mapper.toDTO(newEntry);
        return new ServiceResponse<>(shoppingListDTO);
    }


    private void validateDuplicateTitle(User user, String title) {
        if (validator.isShoppingListTitleExists(user, title)) {
            throw new DuplicateResourceException("Shopping list with title = "
                    + title + " already exists!");
        }
    }
}

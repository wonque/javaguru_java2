package lv.java2.shopping_list.services.shoppinglist.update;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.domain.ShoppingListStatus;
import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.services.shoppinglist.ShoppingListDBValidator;
import lv.java2.shopping_list.web.dto.ShoppingListDTO;
import lv.java2.shopping_list.web.dto.mappers.ShoppingListMapper;
import lv.java2.shopping_list.web.exceptions.ArchivedShoppingListException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShoppingListUpdateService {

    @Autowired
    private ShoppingListRepository repository;
    @Autowired
    private ShoppingListDBValidator validator;
    @Autowired
    private ShoppingListMapper mapper;

    @Transactional
    public ShoppingListDTO update(ShoppingListDTO shoppingListDTO) {
        ShoppingList toUpdate = repository.getOne(shoppingListDTO.getId());
        toUpdate.setTitle(shoppingListDTO.getTitle());
        toUpdate.setCategory(shoppingListDTO.getCategory());
        repository.save(toUpdate);
        return mapper.toDTO(toUpdate);
    }

}

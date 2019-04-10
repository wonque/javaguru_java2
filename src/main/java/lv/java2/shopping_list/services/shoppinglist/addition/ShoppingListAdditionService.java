package lv.java2.shopping_list.services.shoppinglist.addition;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.services.shoppinglist.validation.ShoppingListValidationService;
import lv.java2.shopping_list.web.dto.ShoppingListDTO;
import lv.java2.shopping_list.web.dto.mappers.ShoppingListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShoppingListAdditionService {

    private final ShoppingListRepository repository;
    private final ShoppingListMapper mapper;
    private final ShoppingListValidationService validator;

    @Autowired
    public ShoppingListAdditionService(ShoppingListRepository repository, ShoppingListMapper mapper,
                                       ShoppingListValidationService validator) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Transactional
    public ShoppingListDTO addList(ShoppingListDTO shoppingListDTO) {

        validator.validate(shoppingListDTO);

        ShoppingList newEntry = mapper.toDomain(shoppingListDTO);
        newEntry.markAsActive();
        repository.save(newEntry);

        return mapper.toDTO(newEntry);
    }

}

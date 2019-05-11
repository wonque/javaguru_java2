package lv.java2.shopping_list.services.shoppinglist.update;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.dto.ShoppingListDTO;
import lv.java2.shopping_list.dto.mappers.ShoppingListMapper;
import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.services.shoppinglist.validation.ShoppingListValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShoppingListUpdateService {

    private final ShoppingListRepository repository;
    private final ShoppingListValidationService validator;
    private final ShoppingListMapper mapper;

    @Autowired
    public ShoppingListUpdateService(ShoppingListRepository repository,
                                     ShoppingListValidationService validator,
                                     ShoppingListMapper mapper) {
        this.repository = repository;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Transactional
    public ShoppingListDTO update(ShoppingListDTO listDTO) {
        validator.validate(listDTO);

        ShoppingList toUpdate = repository.getOne(listDTO.getId());
        toUpdate.setTitle(listDTO.getTitle());
        toUpdate.setCategory(listDTO.getCategory());

        toUpdate = repository.save(toUpdate);

        return mapper.toDTO(toUpdate);
    }


}

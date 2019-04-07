package lv.java2.shopping_list.services.shoppinglist.update;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.services.shoppinglist.ShoppingListDBValidator;
import lv.java2.shopping_list.web.dto.ShoppingListDTO;
import lv.java2.shopping_list.web.dto.mappers.ShoppingListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class ShoppingListUpdateService {

    private final ShoppingListRepository repository;
    private final ShoppingListDBValidator validator;
    private final ShoppingListMapper mapper;

    @Autowired
    public ShoppingListUpdateService(ShoppingListRepository repository,
                                     ShoppingListDBValidator validator,
                                     ShoppingListMapper mapper) {
        this.repository = repository;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Transactional
    public ShoppingListDTO update(ShoppingListDTO shoppingListDTO) {

        ShoppingList toUpdate = repository.getOne(shoppingListDTO.getId());
        toUpdate.setTitle(shoppingListDTO.getTitle());
        toUpdate.setCategory(shoppingListDTO.getCategory());

        toUpdate = repository.save(toUpdate);

        return mapper.toDTO(toUpdate);
    }

}

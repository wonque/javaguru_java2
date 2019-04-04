package lv.java2.shopping_list.services.shoppinglist.get;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.services.shoppinglist.ShoppingListDBValidator;
import lv.java2.shopping_list.web.dto.ShoppingListDTO;
import lv.java2.shopping_list.web.dto.mappers.ShoppingListMapper;
import lv.java2.shopping_list.web.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional (readOnly = true)
public class GetShoppingListService {

    @Autowired
    private ShoppingListRepository repository;
    @Autowired
    private ShoppingListDBValidator validator;
    @Autowired
    private ShoppingListMapper mapper;

    @Transactional
    public List<ShoppingListDTO> getAllByUserId(Long userId) {
        validateUserId(userId); //Temporary solution
        return repository.findAllByUserId(userId).stream()
                .map(shoppingList -> mapper.toDTO(shoppingList)).collect(Collectors.toList());
    }

    @Transactional
    public ShoppingListDTO getSingleById(Long userId, Long listId) {
        validateUserId(userId); //Temporary solution
        Optional<ShoppingList> founded = repository.findByUserIdAndListId(userId, listId);
        if (!founded.isPresent()) {
            throw new ResourceNotFoundException
                    (MessageFormat.format("ShoppingList with ID = {0} not found!", listId));
        }
        return mapper.toDTO(founded.get());
    }

    private void validateUserId(Long userId) {
        if (!validator.isUserExists(userId)) {
            throw new ResourceNotFoundException(MessageFormat.format("Unable to find user with ID = {0}", userId));
        }
    }
}

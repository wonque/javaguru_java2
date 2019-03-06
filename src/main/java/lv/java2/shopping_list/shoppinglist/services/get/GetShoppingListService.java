package lv.java2.shopping_list.shoppinglist.services.get;

import lv.java2.shopping_list.ServiceResponse;
import lv.java2.shopping_list.shoppinglist.domain.ShoppingList;
import lv.java2.shopping_list.shoppinglist.repository.ShoppingListRepository;
import lv.java2.shopping_list.shoppinglist.services.ShoppingListDBValidator;
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
@Transactional
public class GetShoppingListService {

    @Autowired
    private ShoppingListRepository repository;
    @Autowired
    private ShoppingListDBValidator validator;
    @Autowired
    private ShoppingListMapper mapper;

    public ServiceResponse<List<ShoppingListDTO>> getAllByUserId(Long userId) {
        //Temporary solution
        validateUserId(userId);
        List<ShoppingListDTO> lists = repository.getAllByUserId(userId).stream()
                .map(shoppingList -> mapper.toDTO(shoppingList)).collect(Collectors.toList());
        return new ServiceResponse<>(lists);
    }

    public ServiceResponse<ShoppingListDTO> getSingleById(Long userId, Long listId) {
        validateUserId(userId);
        Optional<ShoppingList> founded = repository.getByUserIdAndListId(userId, listId);
        if (!founded.isPresent()) {
            throw new ResourceNotFoundException
                    (MessageFormat.format("ShoppingList with ID = {0} not found!", listId));
        }
        ShoppingListDTO shoppingListDTO = mapper.toDTO(founded.get());
        return new ServiceResponse<>(shoppingListDTO);
    }

    private void validateUserId(Long userId) {
        if (!validator.isUserExists(userId)) {
            throw new ResourceNotFoundException(MessageFormat.format("Unable to find user with {id}", userId));
        }
    }
}

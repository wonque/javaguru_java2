package lv.java2.shopping_list.services.shoppinglist.removal;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.domain.ShoppingListStatus;
import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.web.dto.ShoppingListDTO;
import lv.java2.shopping_list.web.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ShoppingListRemovalService {

    private final ShoppingListRepository repository;

    @Autowired
    public ShoppingListRemovalService(ShoppingListRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ShoppingListDTO removeById(Long userId, Long listId) {

        Optional<ShoppingList> founded = repository.findByUserIdAndListId(userId, listId);
        if (founded.isPresent()) {
            repository.delete(founded.get());
            return buildReturnDTO();
        } else {
            throw new ResourceNotFoundException("Shopping list with ID = " + listId + " not found!");
        }
    }

    private ShoppingListDTO buildReturnDTO() {
        ShoppingListDTO dto = new ShoppingListDTO();
        dto.setStatus(ShoppingListStatus.DELETED);
        return dto;
    }

}

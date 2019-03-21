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

    @Autowired
    private ShoppingListRepository repository;

    @Transactional
    public ShoppingListDTO removeById(Long userId, Long listId) {
        Optional<ShoppingList> founded = repository.findByUserIdAndListId(userId, listId);
        if (founded.isPresent()) {
            repository.delete(founded.get());
            return buildDTODeletedResponse();
        } else {
            throw new ResourceNotFoundException("Shopping list with ID = " + listId + " not found!");
        }
    }

    @Transactional
    public ShoppingListDTO removeByTitle(String title) {
        Optional<ShoppingList> founded = repository.findByTitle(title);
        if (founded.isPresent()) {
            repository.delete(founded.get());
            return buildDTODeletedResponse();
        } else {
            throw new ResourceNotFoundException("Shopping list with TITLE = " + title + " not found!");
        }
    }

    private ShoppingListDTO buildDTODeletedResponse(){
        ShoppingListDTO dto = new ShoppingListDTO();
        dto.setStatus(ShoppingListStatus.DELETED);
        return dto;
    }

}

package lv.java2.shopping_list.services.shoppinglist.removal;

import lv.java2.shopping_list.ServiceResponse;
import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.repository.ShoppingListRepository;
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
    public ServiceResponse removeById(Long listId) {
        Optional<ShoppingList> founded = repository.findById(listId);
        if (!founded.isPresent()) {
            throw new ResourceNotFoundException("Shopping list with ID = " + listId + " not found!");
        }
        repository.delete(founded.get());
        return new ServiceResponse<>("Shopping list removed!");
    }

    @Transactional
    public ServiceResponse removeByTitle(String title) {
        Optional<ShoppingList> founded = repository.findByTitle(title);
        if (!founded.isPresent()) {
            throw new ResourceNotFoundException("Shopping list with TITLE = " + title + " not found!");
        }
        repository.delete(founded.get());
        return new ServiceResponse<>(new String("Shopping list " + title + " removed!"));
    }


    //TODO implement Shopping list removal Business Logic
}

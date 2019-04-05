package lv.java2.shopping_list.services.shoppinglist;

import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.domain.User;
import lv.java2.shopping_list.repository.UserRepository;
import lv.java2.shopping_list.web.exceptions.DuplicateResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShoppingListDBValidator {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShoppingListRepository shoppingListRepository;

    /* Temporary method
    Should be removed after security authorization implementation
     */
    public boolean isUserExists(Long userId) {
        return userRepository.findById(userId).isPresent();
    }

    public void isListTitleExists(Long userId, String title) {
        if (shoppingListRepository.findByUserIdAndTitle(userId, title).isPresent()) {
            throw new DuplicateResourceException(String.format("Shopping list with title = '%s' already exists!", title));
        }
    }

}

package lv.java2.shopping_list.shoppinglist.services;

import lv.java2.shopping_list.shoppinglist.repository.ShoppingListRepository;
import lv.java2.shopping_list.user.domain.User;
import lv.java2.shopping_list.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public boolean isShoppingListTitleExists(User user, String title) {
        return shoppingListRepository.getByUserAndTitle(user, title).isPresent();
    }
}

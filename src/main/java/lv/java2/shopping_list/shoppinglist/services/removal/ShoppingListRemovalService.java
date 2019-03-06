package lv.java2.shopping_list.shoppinglist.services.removal;

import lv.java2.shopping_list.shoppinglist.domain.ShoppingListFactory;
import lv.java2.shopping_list.shoppinglist.repository.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListRemovalService {

    @Autowired
    private ShoppingListRepository shoppingListRepository;
    @Autowired
    private ShoppingListFactory factory;


//    @Transactional
//    public ShoppingListRemovalResponse remove(ShoppingListSharedRequest request) {
//        if (!errors.isEmpty()) {
//            return new ShoppingListRemovalResponse(errors);
//        }
//        Optional<ShoppingList> founded = shoppingListRepository.getByUserAndTitle(request.getUser(), request.getTitle());
//        return new ShoppingListRemovalResponse(true);
//    }
}


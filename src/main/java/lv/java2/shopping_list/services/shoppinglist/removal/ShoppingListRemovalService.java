package lv.java2.shopping_list.services.shoppinglist.removal;

import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.web.dto.ShoppingListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShoppingListRemovalService {

    private final ShoppingListRepository repository;

    @Autowired
    public ShoppingListRemovalService(ShoppingListRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void removeById(ShoppingListDTO dto) {
        repository.findByUserIdAndListId(dto.getUserId(), dto.getId())
                .ifPresent(repository::delete);
    }

}

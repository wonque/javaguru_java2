package lv.java2.shopping_list.shoppinglist.services.get;

import lv.java2.shopping_list.shoppinglist.repository.ShoppingListRepository;
import lv.java2.shopping_list.shoppinglist.domain.ShoppingList;
import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.shoppinglist.services.ShoppingListSharedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class GetShoppingListService {

    @Autowired
    private ShoppingListRepository repository;

    public GetShoppingListResponse getSingleShoppingList(ShoppingListSharedRequest request) {
        Optional<ShoppingList> founded = repository.findByAccountAndTitle(request.getAccount(), request.getTitle());
        if (founded.isPresent()) {
            return new GetShoppingListResponse(founded.get());
        } else {
            ShoppingListError error = new ShoppingListError("title", "Shopping list not found!");
            return new GetShoppingListResponse(error);
        }
    }

    public GetShoppingListResponse getAllLists(Long accountId) {
        return new GetShoppingListResponse(repository.findAllListsByAccountId(accountId));
    }

    public GetShoppingListResponse getSingleShoppingListId(Long accountId, Long listId) {
        Optional<ShoppingList> founded = repository.findById(accountId, listId);
        if (founded.isPresent()) {
            return new GetShoppingListResponse(founded.get());
        } else {
            ShoppingListError error = new ShoppingListError("title", "Shopping list not found!");
            return new GetShoppingListResponse(error);
        }
    }
}

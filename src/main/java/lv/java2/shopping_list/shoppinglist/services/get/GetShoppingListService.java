package lv.java2.shopping_list.shoppinglist.services.get;

import lv.java2.shopping_list.shoppinglist.repository.ShoppingListRepository;
import lv.java2.shopping_list.shoppinglist.domain.ShoppingList;
import lv.java2.shopping_list.ShoppingListError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GetShoppingListService {

    @Autowired
    private ShoppingListRepository repository;

    public GetShoppingListResponse getAllLists(Long accountId) {
        List<ShoppingList> lists = repository.findListsByAccountId(accountId);
        return lists.isEmpty() ? new GetShoppingListResponse(lists, HttpStatus.NO_CONTENT)
                : new GetShoppingListResponse(lists, HttpStatus.OK);
    }

    public GetShoppingListResponse getSingleList(Long accountId, Long listId) {
        Optional<ShoppingList> founded = repository.getByAccountIdAndListId(accountId, listId);
        if (founded.isPresent()) {
            return new GetShoppingListResponse(founded.get(), HttpStatus.OK);
        } else {
            ShoppingListError error = new ShoppingListError("title", "Shopping list not found!");
            error.setErrorDatetimeToNow();
            return new GetShoppingListResponse(error, HttpStatus.NOT_FOUND);
        }
    }
}

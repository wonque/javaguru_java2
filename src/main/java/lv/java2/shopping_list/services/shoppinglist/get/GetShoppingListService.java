package lv.java2.shopping_list.services.shoppinglist.get;

import lv.java2.shopping_list.db.ShoppingListRepository;
import lv.java2.shopping_list.domain.account.Account;
import lv.java2.shopping_list.domain.shoppinglist.ShoppingList;
import lv.java2.shopping_list.services.ShoppingListError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetShoppingListService {

    @Autowired
    private ShoppingListRepository repository;

    public GetShoppingListResponse getSingleShoppingList(GetShoppingListRequest request) {
        Optional<ShoppingList> founded = repository.findByAccountAndTitle(request.getAccount(), request.getTitle());
        if (founded.isPresent()) {
            return new GetShoppingListResponse(founded.get());
        } else {
            ShoppingListError error = new ShoppingListError("title", "Shopping list not found!");
            return new GetShoppingListResponse(error);
        }
    }

    public List<ShoppingList> getAllLists(Account account) {
        return repository.findAllLists(account);
    }

}

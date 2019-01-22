package lv.java2.shopping_list.shoppinglist.web;

import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.shoppinglist.services.ShoppingListSharedRequest;
import lv.java2.shopping_list.shoppinglist.services.addition.ShoppingListAdditionResponse;
import lv.java2.shopping_list.shoppinglist.services.addition.ShoppingListAdditionService;
import lv.java2.shopping_list.shoppinglist.services.get.GetShoppingListResponse;
import lv.java2.shopping_list.shoppinglist.services.get.GetShoppingListService;
import lv.java2.shopping_list.shoppinglist.services.removal.ShoppingListRemovalResponse;
import lv.java2.shopping_list.shoppinglist.services.removal.ShoppingListRemovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "accounts/{accountId}/lists")
public class ShoppingListController {

    @Autowired
    private GetShoppingListService getListService;
    @Autowired
    private ShoppingListMapper shoppingListMapper;
    @Autowired
    private ShoppingListAdditionService additionServiced;
    @Autowired
    private ShoppingListRemovalService removalService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllLists(@PathVariable Long accountId) {
        GetShoppingListResponse response = getListService.getAllLists(accountId);
        return ResponseEntity.ok(response.getListOfShoppingLists());
    }

    @RequestMapping(value = "/{listId}", method = RequestMethod.GET)
    public ResponseEntity<?> getSingleList(@PathVariable("accountId") Long accountId,
                                           @PathVariable("listId") Long listId) {
        GetShoppingListResponse response = getListService.getSingleShoppingListId(accountId, listId);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response.getShoppingList());
        }
        return new ResponseEntity<>(response.getError(), HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity createShoppingList(@PathVariable("accountId") Long accountId,
                                           @Validated @RequestBody ShoppingListSharedRequest request) {

        if (isUnauthorized(accountId, request)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(buildUnauthorizedError());
        }

        ShoppingListAdditionResponse response = additionServiced.addList(request);
        if (response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response.getAddedList());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getShoppingListErrors());
    }

    @DeleteMapping
    public ResponseEntity deleteShoppingList(@PathVariable("accountId") Long accountId,
                                             @Validated @RequestBody ShoppingListSharedRequest request) {
        if (isUnauthorized(accountId, request)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(buildUnauthorizedError());
        }
        ShoppingListRemovalResponse response = removalService.remove(request);
        if (response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response.isListRemoved());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getErrors());

    }

    private boolean isUnauthorized(Long accountId, ShoppingListSharedRequest request) {
        return (!request.getAccount().getId().equals(accountId));
    }

    private ShoppingListError buildUnauthorizedError() {
        ShoppingListError error = new ShoppingListError("account",
                "Forbidden");
        error.setStatus(HttpStatus.UNAUTHORIZED);
        return error;
    }

}

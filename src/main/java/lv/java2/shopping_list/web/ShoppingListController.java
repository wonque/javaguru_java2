package lv.java2.shopping_list.web;

import lv.java2.shopping_list.ShoppingListError;
import lv.java2.shopping_list.account.services.get.GetAccountResponse;
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
@RequestMapping(value = "accounts/{accountId}")
public class ShoppingListController {

    @Autowired
    private GetShoppingListService getListService;
    @Autowired
    private ShoppingListAdditionService additionService;
    @Autowired
    private ShoppingListRemovalService removalService;


    @RequestMapping(value = "/lists", method = RequestMethod.GET)
    public ResponseEntity getAllLists(@PathVariable("accountId") Long accountId) {
        GetShoppingListResponse response = getListService.getAllLists(accountId);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    @RequestMapping(value = "/lists/{listId}", method = RequestMethod.GET)
    public ResponseEntity<GetShoppingListResponse> getSingleList(
            @PathVariable("accountId") Long accountId,
            @PathVariable("listId") Long listId) {
        GetShoppingListResponse response = getListService.getSingleList(accountId,listId);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity createShoppingList(@PathVariable("accountId") Long accountId,
                                             @RequestBody ShoppingListSharedRequest request) {

        if (isUnauthorized(accountId, request)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(buildUnauthorizedError());
        }

        ShoppingListAdditionResponse response = additionService.addList(request);
        if (response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response.getAddedList());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getShoppingListErrors());
    }

    @DeleteMapping
    public ResponseEntity deleteShoppingList(@PathVariable("accountId") Long accountId,
                                             @RequestBody ShoppingListSharedRequest request) {
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

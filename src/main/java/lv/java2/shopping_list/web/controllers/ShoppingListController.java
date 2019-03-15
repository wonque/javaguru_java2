package lv.java2.shopping_list.web.controllers;

import lv.java2.shopping_list.ServiceResponse;
import lv.java2.shopping_list.services.shoppinglist.addition.ShoppingListAdditionService;
import lv.java2.shopping_list.services.shoppinglist.get.GetShoppingListService;
import lv.java2.shopping_list.services.shoppinglist.removal.ShoppingListRemovalService;
import lv.java2.shopping_list.web.dto.ShoppingListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "users/{userId}/lists")
public class ShoppingListController {

    @Autowired
    private GetShoppingListService getListService;
    @Autowired
    private ShoppingListAdditionService additionService;
    @Autowired
    private ShoppingListRemovalService removalService;


    @GetMapping
    public ResponseEntity<ServiceResponse> getAllLists(@PathVariable("userId") Long userId) {
        ServiceResponse<List<ShoppingListDTO>> response = getListService.getAllByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ServiceResponse> createList(@PathVariable("userId") Long userId,
                                                      @Validated @RequestBody ShoppingListDTO shoppingListDTO) {
        shoppingListDTO.setUserId(userId);
        ServiceResponse<ShoppingListDTO> response = additionService.addList(shoppingListDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/{listId}")
    public ResponseEntity<ServiceResponse> getSingleById(@PathVariable("userId") Long userId
            , @PathVariable("listId") Long listId) {
        ServiceResponse<ShoppingListDTO> response = getListService.getSingleById(userId, listId);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @DeleteMapping(value = "/{listId}")
    public ResponseEntity removeById(@PathVariable("userId") Long userId,
                                     @PathVariable("listId") Long listId) {
        ServiceResponse<String> response = removalService.removeById(listId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @DeleteMapping
    public ResponseEntity removeByTitle(@PathParam("title") String title) {
        ServiceResponse<String> response = removalService.removeByTitle(title);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);

    }

}

package lv.java2.shopping_list.web.controllers;

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
@RequestMapping (value = "/users/{userId}/lists")
public class ShoppingListController {

    @Autowired
    private GetShoppingListService getListService;
    @Autowired
    private ShoppingListAdditionService additionService;
    @Autowired
    private ShoppingListRemovalService removalService;


    @GetMapping
    public ResponseEntity getAllLists(@PathVariable("userId") Long userId) {
        List<ShoppingListDTO> response = getListService.getAllByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity createList(@PathVariable("userId") Long userId,
                                                      @Validated @RequestBody ShoppingListDTO shoppingListDTO) {
        shoppingListDTO.setUserId(userId);
        ShoppingListDTO response = additionService.addList(shoppingListDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping (value = "/{listId}")
    public ResponseEntity getSingleById(@PathVariable("userId") Long userId
            , @PathVariable("listId") Long listId) {
        ShoppingListDTO response = getListService.getSingleById(userId, listId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/{listId}")
    public ResponseEntity removeById(@PathVariable("userId") Long userId,
                                     @PathVariable("listId") Long listId) {
        ShoppingListDTO response = removalService.removeById(userId, listId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping
    public ResponseEntity removeByTitle(@PathParam("title") String title) {
        ShoppingListDTO response = removalService.removeByTitle(title);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}

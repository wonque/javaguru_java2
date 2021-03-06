package lv.java2.shopping_list.controllers;

import lv.java2.shopping_list.controllers.validation.CreateEntity;
import lv.java2.shopping_list.controllers.validation.UpdateEntity;
import lv.java2.shopping_list.dto.ShoppingListDTO;
import lv.java2.shopping_list.services.shoppinglist.addition.ShoppingListAdditionService;
import lv.java2.shopping_list.services.shoppinglist.get.GetShoppingListService;
import lv.java2.shopping_list.services.shoppinglist.removal.ShoppingListRemovalService;
import lv.java2.shopping_list.services.shoppinglist.update.ShoppingListUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users/{userId}/lists")
public class ShoppingListController {

    @Autowired
    private GetShoppingListService getListService;
    @Autowired
    private ShoppingListAdditionService additionService;
    @Autowired
    private ShoppingListRemovalService removalService;
    @Autowired
    private ShoppingListUpdateService updateService;


    @GetMapping
    public ResponseEntity getAllLists(@PathVariable("userId") Long userId) {

        List<ShoppingListDTO> response = getListService.getAllByUserId(userId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/{listId}")
    public ResponseEntity getSingleById(
            @PathVariable("userId") Long userId,
            @PathVariable("listId") Long listId) {

        ShoppingListDTO response = getListService.getSingleById(userId, listId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity createList(@PathVariable("userId") Long userId,
                                     @Validated(CreateEntity.class) @RequestBody ShoppingListDTO shoppingListDTO) {

        shoppingListDTO.setUserId(userId);

        ShoppingListDTO response = additionService.addList(shoppingListDTO);
        URI selfLocation = buildLocationUri(response.getId());

        return ResponseEntity.status(HttpStatus.CREATED).location(selfLocation).body(response);
    }


    @PutMapping(value = "/{listId}")
    public ResponseEntity updateList(@PathVariable("userId") Long userId,
                                     @PathVariable("listId") Long listId,
                                     @Validated(UpdateEntity.class) @RequestBody ShoppingListDTO shoppingListDTO) {

        shoppingListDTO.setUserId(userId);
        shoppingListDTO.setId(listId);

        ShoppingListDTO response = updateService.update(shoppingListDTO);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/{listId}")
    public ResponseEntity removeById(@PathVariable("userId") Long userId,
                                     @PathVariable("listId") Long listId) {

        ShoppingListDTO requestDTO = new ShoppingListDTO();
        requestDTO.setId(listId);
        requestDTO.setUserId(userId);

        removalService.removeById(requestDTO);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    private URI buildLocationUri(Long listId) {
        return ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{listId}")
                .build(listId);
    }
}

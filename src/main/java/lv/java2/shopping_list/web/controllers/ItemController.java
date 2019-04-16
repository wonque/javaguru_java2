package lv.java2.shopping_list.web.controllers;

import lv.java2.shopping_list.services.item.addition.ItemAdditionService;
import lv.java2.shopping_list.web.dto.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users/{userId}/lists/{listId}/items")
public class ItemController {

    @Autowired
    private ItemAdditionService additionService;

    @PostMapping
    public ResponseEntity createItem(@PathVariable("userId") Long userId,
                                     @PathVariable("listId") Long listId,
                                     @RequestBody ItemDTO itemDTO) {

        ItemDTO response = additionService.addItem(itemDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}

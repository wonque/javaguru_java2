package lv.java2.shopping_list.web;

import lv.java2.shopping_list.ServiceResponse;
import lv.java2.shopping_list.shoppinglist.services.addition.ShoppingListAdditionService;
import lv.java2.shopping_list.shoppinglist.services.get.GetShoppingListService;
import lv.java2.shopping_list.web.dto.ShoppingListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "users/{userId}")
public class ShoppingListController {

    @Autowired
    private GetShoppingListService getListService;
    @Autowired
    private ShoppingListAdditionService additionService;


    @RequestMapping(value = "/lists", method = RequestMethod.GET)
    public ResponseEntity<ServiceResponse> getAllLists(@PathVariable("userId") Long userId) {
        ServiceResponse<List<ShoppingListDTO>> response = getListService.getAllByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(value = "/lists")
    public ResponseEntity<ServiceResponse> createList(@PathVariable("userId") Long userId,
                                                      @Validated @RequestBody ShoppingListDTO shoppingListDTO) {
        shoppingListDTO.setUserId(userId);
        ServiceResponse<ShoppingListDTO> response = additionService.addList(shoppingListDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}

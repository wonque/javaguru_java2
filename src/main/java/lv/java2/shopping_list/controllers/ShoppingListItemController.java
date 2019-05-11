package lv.java2.shopping_list.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users/{userId}/lists/{listId}/items")
public class ShoppingListItemController {

}

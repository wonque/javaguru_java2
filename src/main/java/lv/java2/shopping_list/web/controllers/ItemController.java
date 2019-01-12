package lv.java2.shopping_list.web.controllers;

import lv.java2.shopping_list.db.ShoppingListItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/accounts/{id}/lists/{listId}")
public class ItemController {

    @Autowired
    private ShoppingListItemRepository itemRepository;


}

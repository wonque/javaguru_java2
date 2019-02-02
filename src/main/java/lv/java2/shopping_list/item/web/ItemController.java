package lv.java2.shopping_list.item.web;

import lv.java2.shopping_list.item.domain.Item;
import lv.java2.shopping_list.item.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/accounts/{id}/lists/{listId}")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;


}

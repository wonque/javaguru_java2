package lv.java2.shopping_list.web.controllers;

import lv.java2.shopping_list.domain.account.Account;
import lv.java2.shopping_list.domain.shoppinglist.ShoppingList;
import lv.java2.shopping_list.services.shoppinglist.get.GetShoppingListService;
import lv.java2.shopping_list.web.dtos.AccountDTO;
import lv.java2.shopping_list.web.dtos.ShoppingListDTO;
import lv.java2.shopping_list.web.dtos.mappers.AccountMapper;
import lv.java2.shopping_list.web.dtos.mappers.ShoppingListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/accounts/{id}/lists")
public class ShoppingListController {

    @Autowired
    private GetShoppingListService getShoppingListService;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private ShoppingListMapper shoppingListMapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllLists(@PathVariable("id") Long accountId, AccountDTO accountDTO) {
        Account account = accountMapper.dtoToDomain(accountDTO);
        List<ShoppingListDTO> responseList = new ArrayList<>();
        getShoppingListService.getAllLists(account)
                .forEach(shoppingList -> responseList.add(shoppingListMapper.domainToDto(shoppingList)));
        return ResponseEntity.ok(responseList);
    }
}

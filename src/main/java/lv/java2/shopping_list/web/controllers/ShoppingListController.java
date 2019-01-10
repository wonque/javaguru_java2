package lv.java2.shopping_list.web.controllers;

import lv.java2.shopping_list.domain.account.Account;
import lv.java2.shopping_list.domain.shoppinglist.ShoppingList;
import lv.java2.shopping_list.services.shoppinglist.get.GetShoppingListService;
import lv.java2.shopping_list.web.dtos.AccountDTO;
import lv.java2.shopping_list.web.mapping.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShoppingListController {

    @Autowired
    private GetShoppingListService getShoppingListService;
    @Autowired
    private AccountMapper accountMapper;

    @RequestMapping(value = "/accounts/{id}/lists", method = RequestMethod.GET)
    public List<ShoppingList> getAllLists(@PathVariable("id") Long accountId, AccountDTO accountDTO) {
        Account account = accountMapper.dtoToDomain(accountDTO);
        return getShoppingListService.getAllLists(account);
    }
}

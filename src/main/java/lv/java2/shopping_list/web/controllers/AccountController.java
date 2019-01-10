package lv.java2.shopping_list.web.controllers;


import lv.java2.shopping_list.domain.account.Account;
import lv.java2.shopping_list.domain.shoppinglist.ShoppingList;
import lv.java2.shopping_list.services.account.get.GetAccountRequest;
import lv.java2.shopping_list.services.account.get.GetAccountResponse;
import lv.java2.shopping_list.services.account.get.GetAccountService;
import lv.java2.shopping_list.services.account.registration.AccountRegistrationRequest;
import lv.java2.shopping_list.services.account.registration.AccountRegistrationResponse;
import lv.java2.shopping_list.services.account.registration.AccountRegistrationServiceImpl;
import lv.java2.shopping_list.services.shoppinglist.get.GetShoppingListRequest;
import lv.java2.shopping_list.services.shoppinglist.get.GetShoppingListService;
import lv.java2.shopping_list.web.dtos.AccountDTO;
import lv.java2.shopping_list.web.mapping.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountRegistrationServiceImpl registrationService;

    @Autowired
    private GetAccountService getAccountService;

    @Autowired
    private AccountMapper accountMapper;

    @PostMapping("/accounts")
    public ResponseEntity<?> register(@RequestBody AccountRegistrationRequest registrationRequest) {
        AccountRegistrationResponse registrationResponse = registrationService.register(registrationRequest);
        if (registrationResponse.isSuccess()) {
            URI uri = buildUri(registrationResponse.getAccount().getId());
            HttpHeaders headers = buildHeaders(uri);
            return ResponseEntity.ok().headers(headers).build();
        } else {
            return new ResponseEntity<>(registrationResponse.getErrors(), HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAccount(@PathVariable ("id") Long id) {
        GetAccountRequest request = new GetAccountRequest(id);
        GetAccountResponse response = getAccountService.get(request);
        if (response.isSuccess()) {
            URI uri = buildUri(response.getAccount().getId());
            HttpHeaders headers = buildHeaders(uri);
            return ResponseEntity.ok().headers(headers).build();
        } else {
            return new ResponseEntity<>(response.getError(), HttpStatus.NOT_FOUND);
        }
    }

    private HttpHeaders buildHeaders(URI uri) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);
        return headers;
    }

    private URI buildUri(Long accountId) {
        return ServletUriComponentsBuilder.fromCurrentServletMapping().
                path("/accounts/{id}/lists").build().expand(accountId).toUri();
    }

}

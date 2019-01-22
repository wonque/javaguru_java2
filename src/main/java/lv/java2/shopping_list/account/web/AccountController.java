package lv.java2.shopping_list.account.web;


import lv.java2.shopping_list.account.domain.Account;
import lv.java2.shopping_list.account.services.get.GetAccountRequest;
import lv.java2.shopping_list.account.services.get.GetAccountResponse;
import lv.java2.shopping_list.account.services.get.GetAccountService;
import lv.java2.shopping_list.account.services.registration.AccountRegistrationRequest;
import lv.java2.shopping_list.account.services.registration.AccountRegistrationResponse;
import lv.java2.shopping_list.account.services.registration.AccountRegistrationServiceImpl;
import lv.java2.shopping_list.shoppinglist.web.ShoppingListController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    @Autowired
    private AccountRegistrationServiceImpl registrationService;

    @Autowired
    private GetAccountService getAccountService;

    @Autowired
    private AccountMapper accountMapper;

    @PostMapping
    public ResponseEntity<?> register(@Validated @RequestBody AccountRegistrationRequest request) {
        AccountRegistrationResponse response = registrationService.register(request);
        if (response.isSuccess()) {
            AccountDTO accountDTO = accountMapper.accountToAccountDTO(response.getAccount());
            accountDTO.setRegistered(true);

            Link linkToAccount = linkTo(methodOn(AccountController.class)
                    .getAccount(accountDTO.getId()))
                    .withRel("account");
            Link linkToShoppingLists = linkTo(methodOn(ShoppingListController.class)
                    .getAllLists(accountDTO.getId())).withRel("shopping lists");

            Resource<AccountDTO> responseResource = new Resource<>(accountDTO);
            responseResource.add(linkToAccount);
            responseResource.add(linkToShoppingLists);

            return ResponseEntity.ok().body(responseResource);
        } else {
            return new ResponseEntity<>(response.getErrors(), HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/hal+json"})
    public ResponseEntity<?> getAccount(@PathVariable(value = "id") Long id) {
        GetAccountRequest request = new GetAccountRequest(id);
        GetAccountResponse response = getAccountService.get(request);
        if (response.isSuccess()) {
            Resource<Link> resource = buildResponseResource(response.getAccount());
            return ResponseEntity.ok().body(resource);
        } else {
            return new ResponseEntity<>(response.getError(), HttpStatus.NOT_FOUND);
        }
    }

    private Resource<Link> buildResponseResource(Account account) {
        Link link = linkTo(methodOn(ShoppingListController.class)
                .getAllLists(account.getId())).withRel("allLists");
        return new Resource<>(link);
    }

}

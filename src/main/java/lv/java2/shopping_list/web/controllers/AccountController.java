package lv.java2.shopping_list.web.controllers;


import lv.java2.shopping_list.domain.account.Account;
import lv.java2.shopping_list.services.account.get.GetAccountRequest;
import lv.java2.shopping_list.services.account.get.GetAccountResponse;
import lv.java2.shopping_list.services.account.get.GetAccountService;
import lv.java2.shopping_list.services.account.registration.AccountRegistrationRequest;
import lv.java2.shopping_list.services.account.registration.AccountRegistrationResponse;
import lv.java2.shopping_list.services.account.registration.AccountRegistrationServiceImpl;
import lv.java2.shopping_list.web.dtos.AccountDTO;
import lv.java2.shopping_list.web.dtos.mappers.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AccountRegistrationRequest request) {
        AccountRegistrationResponse response = registrationService.register(request);
        if (response.isSuccess()) {
            Resource<AccountDTO> responseResource = buildResponseResource(response.getAccount());
            return ResponseEntity.ok().body(responseResource);
        } else {
            return new ResponseEntity<>(response.getErrors(), HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/hal+json"})
    public ResponseEntity<?> getAccount(@PathVariable("id") Long id) {
        GetAccountRequest request = new GetAccountRequest(id);
        GetAccountResponse response = getAccountService.get(request);
        if (response.isSuccess()) {
            Resource<AccountDTO> resource = buildResponseResource(response.getAccount());
            return ResponseEntity.ok().body(resource);
        } else {
            return new ResponseEntity<>(response.getError(), HttpStatus.NOT_FOUND);
        }
    }

    private Resource<AccountDTO> buildResponseResource(Account account) {
        AccountDTO accountDTO = accountMapper.accountToAccountDTO(account);
        Link link = linkTo(methodOn(ShoppingListController.class)
                .getAllLists(accountDTO.getId(), accountDTO))
                .withSelfRel()
                .withTitle(accountDTO.getUserName() + " shopping lists");
        Resource<AccountDTO> resource = new Resource<>(accountDTO);
        resource.add(link);
        return resource;
    }

}

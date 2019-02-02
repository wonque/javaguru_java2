package lv.java2.shopping_list.web;

import lv.java2.shopping_list.account.domain.Account;
import lv.java2.shopping_list.account.services.get.GetAccountResponse;
import lv.java2.shopping_list.account.services.get.GetAccountService;
import lv.java2.shopping_list.account.services.registration.AccountRegistrationResponse;
import lv.java2.shopping_list.account.services.registration.AccountRegistrationService;
import lv.java2.shopping_list.web.dto.AccountDTO;
import lv.java2.shopping_list.web.exceptions.CustomExceptionHandlerTmp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
public class AccountController {

    @Autowired
    private AccountRegistrationService registrationService;

    @Autowired
    private GetAccountService getAccountService;


    @PostMapping
    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public ResponseEntity<AccountRegistrationResponse> signUp(@Validated @RequestBody AccountDTO accountDTO) {
        AccountRegistrationResponse response = registrationService.register(accountDTO);
        addLinkToSelf(response);
        return ResponseEntity
                .status(response.getHttpStatus())
                .body(response);
    }

    @RequestMapping(value = "/accounts/{accountId}", method = RequestMethod.GET)
    public ResponseEntity getAccount(@PathVariable("accountId") Long accountId) {
        GetAccountResponse response = getAccountService.getById(accountId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    private Link linkToAccount(Account account) {
        return linkTo(methodOn(AccountController.class).getAccount(account.getId())).
                withSelfRel();
    }

    private void addLinkToSelf(AccountRegistrationResponse response) {
        if (response.getHttpStatus().is2xxSuccessful()) {
            response.add(linkTo(methodOn(AccountController.class)
                    .getAccount(response.getAccountDTO().getId())).
                    withSelfRel());
        }
    }

}

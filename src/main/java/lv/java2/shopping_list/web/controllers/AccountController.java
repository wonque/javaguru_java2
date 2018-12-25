package lv.java2.shopping_list.web.controllers;

import lv.java2.shopping_list.services.account.get.GetAccountService;
import lv.java2.shopping_list.services.account.login.AccountLoginRequest;
import lv.java2.shopping_list.services.account.login.AccountLoginResponse;
import lv.java2.shopping_list.services.account.login.AccountLoginService;
import lv.java2.shopping_list.services.account.registration.AccountRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountRegistrationService registrationService;

    @Autowired
    private GetAccountService getAccountService;

    @Autowired
    private AccountLoginService loginService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> login(@RequestBody String login, String password){
        AccountLoginRequest request = new AccountLoginRequest(login,password);
        AccountLoginResponse response = loginService.login(request);
        if(response.isLoggedIn()){
            return ResponseEntity.status(200).body("success!");
        }else{
            return ResponseEntity.status(401).body(response.getError().getErrorDescription());
        }
    }

}

package lv.java2.shopping_list.views;


import lv.java2.shopping_list.services.account.registration.AccountRegistrationRequest;
import lv.java2.shopping_list.services.account.registration.AccountRegistrationResponse;
import lv.java2.shopping_list.services.account.registration.AccountRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AccountRegistrationView {

    @Autowired
    private AccountRegistrationService registrationService;


    public void register() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter login: ");
        String login = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        System.out.println("Enter username: ");
        String userName = scanner.nextLine();
        AccountRegistrationRequest registrationRequest = new AccountRegistrationRequest(login, password);
        registrationRequest.setUserName(userName);
        AccountRegistrationResponse response = registrationService.register(registrationRequest);
        if (response.isSuccess()) {
            System.out.println("REGISTERED!");
        } else {
            response.displayErrors();
        }
    }
}

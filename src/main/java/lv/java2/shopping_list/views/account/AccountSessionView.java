package lv.java2.shopping_list.views.account;

import lv.java2.shopping_list.domain.Account;
import lv.java2.shopping_list.services.session.AccountSessionRequest;
import lv.java2.shopping_list.services.session.AccountSessionResponse;
import lv.java2.shopping_list.services.session.AccountSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.ws.soap.Addressing;
import java.util.Scanner;

@Component
public class AccountSessionView {

    @Autowired
    private AccountSessionService sessionService;


    public AccountSessionResponse loginToAccount() {
        AccountSessionResponse response = null;
        boolean loggedIn = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Login process started!\n");
        while (!loggedIn) {
            System.out.println("Enter email as login: ");
            String login = scanner.nextLine();
            System.out.println("Enter password: ");
            String password = scanner.nextLine();
            AccountSessionRequest request = new AccountSessionRequest(login, password);
            response = sessionService.execute(request);
            if (response.isSuccess()) {
                loggedIn = true;
            } else {
                System.out.println("Could not login to account!");
                System.out.println("Check your password or login and try again!\n");
                System.out.println(response.getError().getField());
                System.out.println(response.getError().getErrorDescription());
            }
        }
        return response;

    }
}


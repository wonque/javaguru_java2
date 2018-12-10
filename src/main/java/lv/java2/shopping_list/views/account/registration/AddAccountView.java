package lv.java2.shopping_list.views.account.registration;

import lv.java2.shopping_list.services.add.account.registration.AccountRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddAccountView {

    @Autowired
    private AccountRegistrationService accountRegistrationService;



    private String getUsername() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        return scanner.nextLine();
    }

    private void printPasswordRules() {
        System.out.println("\nPassword must contain at least one digit");
        System.out.println("Passwords minimum length is 6 symbols\n");
    }
}


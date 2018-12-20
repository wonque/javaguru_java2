package lv.java2.shopping_list.views.account;

import lv.java2.shopping_list.services.account.registration.AccountRegistrationRequest;
import lv.java2.shopping_list.services.account.registration.AccountRegistrationResponse;
import lv.java2.shopping_list.services.account.registration.AccountRegistrationService;
import lv.java2.shopping_list.services.account.validation.AccountRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AccountRegistrationView {

    @Autowired
    private AccountRegistrationService accountRegistrationService;

    @Autowired
    private AccountRegistrationValidator validator;

    public void execute() {
        System.out.println("Account registration process started!\n");
        String login = getLoginFromUser();
        String password = getMainPasswordFromUser();
        boolean confirmed = false;
        while (!confirmed) {
            confirmed = confirmMainPassword(password);
        }
        String userName = getUsername();
        AccountRegistrationRequest registrationRequest = new AccountRegistrationRequest(login, password);
        registrationRequest.setUserName(userName);
        AccountRegistrationResponse response = accountRegistrationService.register(registrationRequest);
        if (response.isSuccess()) {
            System.out.println("ACCOUNT SUCCESSFULLY REGISTERED");
        } else {
            response.getErrors().forEach(error -> System.out.println(error));
        }
    }


    private String getLoginFromUser() {
        String login;
        boolean validated = false;
        Scanner scanner = new Scanner(System.in);
        do {
            displayLoginRules();
            System.out.println("\nEnter your email as login: ");
            login = scanner.nextLine();
            if (validator.validateLogin(login)) {
                validated = true;
            } else {
                System.out.println("Not a valid login!");
            }
        } while (!validated);
        return login;
    }

    private String getMainPasswordFromUser() {
        String mainPass;
        Scanner scanner = new Scanner(System.in);
        do {
            displayPasswordRules();
            System.out.println("\nEnter main password: ");
            mainPass = scanner.nextLine();
        } while (!validator.validatePassword(mainPass));
        return mainPass;
    }

    private boolean confirmMainPassword(String mainPass) {
        String confirmedPass;
        boolean confirmed = false;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Confirm main password: ");
            confirmedPass = scanner.nextLine();
            if (matchPasswords(confirmedPass, mainPass)) {
                confirmed = true;
            } else {
                System.out.println("Confirmed password must be the same as main password!");
            }
        } while (!confirmed);
        return confirmed;
    }

    private void displayPasswordRules() {
        System.out.println("\n======PASSWORD RULES=====");
        System.out.println("Password must have at least 6 characters");
        System.out.println("Password must contain at least one digit");
        System.out.println("=========================\n");
    }

    private void displayLoginRules() {
        System.out.println("\n=====LOGIN RULES=====");
        System.out.println("Login must contain letters and numbers");
        System.out.println("Login cannot be empty!");
        System.out.println("Login must contain @ and . characters!");
        System.out.println("=====================\n");

    }

    private String getUsername() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your user name: ");
        return scanner.nextLine();
    }

    private boolean matchPasswords(String mainPass, String confirmed) {
        return mainPass.equals(confirmed);
    }
}

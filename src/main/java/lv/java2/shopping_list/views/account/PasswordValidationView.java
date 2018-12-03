package lv.java2.shopping_list.views.account;

import lv.java2.shopping_list.services.add.account.validation.password.PasswordValidationRequest;
import lv.java2.shopping_list.services.add.account.validation.password.PasswordValidationResponse;
import lv.java2.shopping_list.services.add.account.validation.password.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Scanner;

@Component
public class PasswordValidationView {

    @Autowired
    private PasswordValidator passwordValidator;


    public String getValidatedPassword() {
        String primaryPassword;
        String confirmedPassword;
        PasswordValidationResponse response;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter password: ");
            primaryPassword = scanner.nextLine();
            System.out.println("Confirm password: ");
            confirmedPassword = scanner.nextLine();
            PasswordValidationRequest request = new PasswordValidationRequest(primaryPassword, confirmedPassword);
            response = passwordValidator.validatePassword(request);
            if (!response.isSuccess()) {
                response.displayErrors();
            }
        } while (!response.isSuccess());
        return primaryPassword;
    }
}

package lv.java2.shopping_list.views.account;

import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.add.account.validation.login.LoginValidationRequest;
import lv.java2.shopping_list.services.add.account.validation.login.LoginValidationResponse;
import lv.java2.shopping_list.services.add.account.validation.login.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class LoginValidationView {

    @Autowired
    private LoginValidator loginValidator;

    public String getValidatedLogin() {
        String login;
        LoginValidationResponse response;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your email as login: ");
            login = scanner.nextLine();
            LoginValidationRequest request = new LoginValidationRequest(login);
            response = loginValidator.validateLogin(login);
            if (!response.isSuccess()) {
                response.displayErrors();
            }
        } while (!response.isSuccess());
        return login;
    }
}

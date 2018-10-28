package views;

import services.UserInputService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInputView {

    private UserInputService userInputService;

    public UserInputView (UserInputService service){
        userInputService = service;
    }

    private void printMenuOption() {
        System.out.println("Please, choose an option: \n");
        System.out.println("1: Add product to list");
        System.out.println("2: Remove product from list");
        System.out.println("3: Print list");
        System.out.println("0: Terminate program\n");
        System.out.println();
    }

    public int getUserInput() throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        printMenuOption();
        int ans;
        System.out.println("Your option: ");
        try {
            ans = scanner.nextInt();
            if (userInputService.isUserAnswerValid(ans)) {
                return ans;
            } else {
                System.out.println("Not a valid option!\n");
                ans = getUserInput();
            }

        } catch (InputMismatchException e) {
            System.out.println("This is not a number!\n");
            scanner.next();
            ans = getUserInput();
        }
        return ans;
    }
}

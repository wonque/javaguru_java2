package lv.java2.shopping_list.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lv.java2.shopping_list.services.UserInputValidation;

import java.util.Scanner;

@Component
public class UserInputGetters {

    @Autowired
    private UserInputValidation validationService;

    public UserInputGetters(UserInputValidation validationService) {
        this.validationService = validationService;
    }

    public String getProductTitleFromUser() {
        String title;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Type product title: ");
            title = scanner.nextLine();
        } while (validationService.isEnteredProductTitleEmpty(title));
        return title;
    }

    public String getProductCategoryFromUser() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println("Type product category: ");
        return scanner.nextLine();
    }

    public double getProductPriceFromUser() {
        double input;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter positive number for price: ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid input!");
                scanner.next();
            }
            input = scanner.nextDouble();
        } while (!validationService.isUserEnteredPriceBiggerThanZero(input));
        return input;
    }

    public int getFromUserDetailMenuItemToExecute() {
        int option;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Select menu option: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input!");
                scanner.next();
            }
            option = scanner.nextInt();
        }
        while (!validationService.isMainMenuUserInputValid(option));
        return option;
    }

    public String getProductDescriptionFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type product description: ");
        return scanner.nextLine();
    }
}


package views;

import services.UserMenuService;

import java.util.Scanner;

public class UserMenuView {

    private Scanner scanner;
    private UserMenuService menuService;

    public UserMenuView() {
        scanner = new Scanner(System.in);
        menuService = new UserMenuService();
    }

    public void printMenuOption() {
        System.out.println("Please, choose an option: \n");
        System.out.println("1: Add product to list");
        System.out.println("2: Remove product from list");
        System.out.println("3: Print list");
        System.out.println("0: Terminate program\n");
    }

    public void printProductDetailsSettingsMenu() {
        System.out.println("1: add product description");
        System.out.println("2: add product price");
        System.out.println("3: add product category");
        System.out.println("0: finish\n");
    }

    public int getDetailsMenuItemToExecute() {
        int option;
        do {
            System.out.println("Select menu option: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input!");
                scanner.nextLine();
            }
            option = scanner.nextInt();
        }
        while (!menuService.isUserInputValid(option));
        return option;
    }


}

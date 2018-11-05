import db.ProductBase;
import db.InMemoryDataBase;
import db.jdbc.PostgreJDBC;
import db.jdbc.ProductBaseImpl;
import services.*;
import views.*;

import java.util.Scanner;

public class ShoppingListApp {


    public static void main(String[] args) {
        SetProductDetailsService productDetailsService = new SetProductDetailsService();
        ProductBase database = new ProductBaseImpl(productDetailsService);

//        ProductPriceService priceService = new ProductPriceService();
//        UserMenuService menuService = new UserMenuService();

        AddProductService addProductService = new AddProductService(database);
        AddProductView addProductView = new AddProductView(addProductService);

        RemoveProductService removeProductService = new RemoveProductService(database);
        RemoveProductView removeProductView = new RemoveProductView(removeProductService);

        GetShoppingListService getShoppingList = new GetShoppingListService(database);
        PrintShoppingListView printList = new PrintShoppingListView(getShoppingList);

        while (true) {
            printMenuOption();
            int option = getFromUserMenuItemToExecute();
            if (option == 0) {
                break;
            }
            switch (option) {
                case 1:
                    addProductView.execute();
                    break;
                case 2:
                    removeProductView.execute();
                    break;
                case 3:
                    printList.execute();
                    break;
            }
        }
    }

    public static void printMenuOption() {
        System.out.println("Please, choose an option: \n");
        System.out.println("1: Add product to list");
        System.out.println("2: Remove product from list");
        System.out.println("3: Print list");
        System.out.println("0: Terminate program\n");
    }

    public static int getFromUserMenuItemToExecute() {
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
        while (!isUserInputValid(option));
        return option;
    }

    private static boolean isUserInputValid(int input) {
        return (input >= 0 && input <= 3);
    }


}







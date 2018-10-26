import db.Database;
import db.InMemoryDataBase;
import services.AddProductService;
import services.GetShoppingListService;
import services.RemoveProductService;
import views.AddProductView;
import views.PrintShoppingListView;
import views.RemoveProductView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ShoppingListApp {


    public static void main(String[] args) {
        Database database = new InMemoryDataBase();

        AddProductService addProductService = new AddProductService(database);
        AddProductView addProductView = new AddProductView(addProductService);
        RemoveProductService removeProductService = new RemoveProductService(database);
        RemoveProductView removeProductView = new RemoveProductView(removeProductService);

        GetShoppingListService getShoppingList = new GetShoppingListService(database);
        PrintShoppingListView printList = new PrintShoppingListView(getShoppingList);

        while (true) {
            int userInput = getUserInput();
            if (userInput == 0) {
                break;
            }
            switch (userInput) {
                case 1:
                    addProductView.execute();
                    ;
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

    public static boolean isUserAnswerValid(int ans) {
        return (ans >= 0 && ans <= 3);
    }

    public static void printMenuOption() {
        System.out.println("Please, choose an option: \n");
        System.out.println("1: Add product to list");
        System.out.println("2: Remove product from list");
        System.out.println("3: Print list");
        System.out.println("0: Terminate program\n");
        System.out.println();
    }

    public static int getUserInput() throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        printMenuOption();
        int ans;
        System.out.println("Your option: ");
        try {
            ans = scanner.nextInt();
            if (isUserAnswerValid(ans)) {
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







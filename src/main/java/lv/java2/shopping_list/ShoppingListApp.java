package lv.java2.shopping_list;

import lv.java2.shopping_list.config.SpringAppConfig;
import lv.java2.shopping_list.views.AddShoppingListView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class ShoppingListApp {


    public static void main(String[] args) {
        ApplicationContext context
                = new AnnotationConfigApplicationContext(SpringAppConfig.class);

        AddShoppingListView addShoppingListView = context.getBean(AddShoppingListView.class);
//        addShoppingListView.execute();
//        AddAccountView addAccountView = context.getBean(AddAccountView.class);
//        addAccountView.execute();
//        AccountRepositoryImpl accountRepository = context.getBean(AccountRepositoryImpl.class);
//        AddProductView addProductView = context.getBean(AddProductView.class);
//        RemoveProductView removeProductView = context.getBean(RemoveProductView.class);
//        Optional<Account> accountOptional = accountRepository.findAccountByLogin("login@tv.lv");
//        if(accountOptional.isPresent()){
//            System.out.println("SUCCESS!");
//        }else{
//            System.out.println("FAIL");
//        }}

//        while (true) {
//            printMenuOption();
//            int option = getFromUserMenuItemToExecute();
//            if (option == 0) {
//                break;
//            }
//            switch (option) {
//                case 1:
//                    addProductView.execute();
//                    break;
//                case 2:
//                    removeProductView.execute();
//                    break;
//            }
//        }
    }


    public static void printMenuOption() {
        System.out.println("Please, choose an option: \n");
        System.out.println("1: Add product to list");
        System.out.println("2: Remove product from list");
//        System.out.println("3: Print list");
        System.out.println("0: Terminate program\n");
    }

    public static int getFromUserMenuItemToExecute() {
        int option;
        do {
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            System.out.println("Select menu option: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input!");
                scanner.next();
            }
            option = scanner.nextInt();
        }
        while (!inputEqualOrBiggerZeroLessOrEqual4(option));
        return option;
    }

    private static boolean inputEqualOrBiggerZeroLessOrEqual4(int input) {
        return (input >= 0 && input <= 4);
    }

}








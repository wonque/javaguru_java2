import config.SpringAppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import views.*;


public class ShoppingListApp {


    public static void main(String[] args) {
        ApplicationContext context
                = new AnnotationConfigApplicationContext(SpringAppConfig.class);

//        ProductRepositoryImpl database = new ProductRepositoryImpl();

//        UserInputValidation validationService = new UserInputValidation();
//        UserInputGetters inputGetters = new UserInputGetters(validationService);

//        AddProductService addProductService = new AddProductService(database);
//        AddProductView addProductView = new AddProductView(addProductService);

//        ProductDescriptionService descriptionService = new ProductDescriptionService(database);
//        ProductPriceService priceService = new ProductPriceService(database);
//        ProductDetailsView productDetailsView = new ProductDetailsView(inputGetters, descriptionService,
//                priceService);
//
//        RemoveProductService removeProductService = new RemoveProductService(database);
//        RemoveProductView removeProductView = new RemoveProductView(removeProductService);
//
//        GetShoppingListService getShoppingList = new GetShoppingListService(database);
//        PrintShoppingListView printList = new PrintShoppingListView(getShoppingList);

        UserInputGetters inputGetters = context.getBean(UserInputGetters.class);
        AddProductView addProductView = context.getBean(AddProductView.class);
        ProductDetailsView productDetailsView = context.getBean(ProductDetailsView.class);
        RemoveProductView removeProductView = context.getBean(RemoveProductView.class);
        PrintShoppingListView printList = context.getBean(PrintShoppingListView.class);

        while (true) {
            printMenuOption();
            int option = getFromUserMenuItemToExecute();
            if (option == 0) {
                break;
            }
            switch (option) {
                case 1:
                    String title = inputGetters.getProductTitleFromUser();
                    addProductView.execute(title);
                    productDetailsView.execute(title);
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
            java.util.Scanner scanner = new java.util.Scanner(System.in);
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







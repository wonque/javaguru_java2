import db.Database;
import db.InMemoryDataBase;
import services.AddProductService;
import services.GetShoppingListService;
import services.RemoveProductService;
import services.UserInputService;
import views.AddProductView;
import views.PrintShoppingListView;
import views.RemoveProductView;
import views.UserInputView;

public class ShoppingListApp {


    public static void main(String[] args) {
        Database database = new InMemoryDataBase();

        UserInputService userInputService = new UserInputService();
        UserInputView userInputView = new UserInputView(userInputService);

        AddProductService addProductService = new AddProductService(database);
        AddProductView addProductView = new AddProductView(addProductService);
        RemoveProductService removeProductService = new RemoveProductService(database);
        RemoveProductView removeProductView = new RemoveProductView(removeProductService);

        GetShoppingListService getShoppingList = new GetShoppingListService(database);
        PrintShoppingListView printList = new PrintShoppingListView(getShoppingList);

        while (true) {
            int option = userInputView.getUserInput();
            if (option == 0) {
                break;
            }
            switch (option) {
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

}







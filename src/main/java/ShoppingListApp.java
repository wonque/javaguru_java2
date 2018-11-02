import db.Database;
import db.InMemoryDataBase;
import services.*;
import views.*;

public class ShoppingListApp {


    public static void main(String[] args) {
        Database database = new InMemoryDataBase();

        UserMenuView userMenuView = new UserMenuView();

        ProductPriceService priceService = new ProductPriceService();
        AddProductDetailsService detailsService = new AddProductDetailsService(priceService);
        AddProductDetailsView addProductDetailsView = new AddProductDetailsView(detailsService);
        AddProductService addProductService = new AddProductService(database);
        AddProductView addProductView = new AddProductView(addProductService, addProductDetailsView);

        RemoveProductService removeProductService = new RemoveProductService(database);
        RemoveProductView removeProductView = new RemoveProductView(removeProductService);

        GetShoppingListService getShoppingList = new GetShoppingListService(database);
        PrintShoppingListView printList = new PrintShoppingListView(getShoppingList);

        while (true) {
            userMenuView.printMenuOption();
            int option = userMenuView.getDetailsMenuItemToExecute();
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







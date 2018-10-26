import java.util.List;

public class GetShoppingListService {

    Database database;

    public GetShoppingListService(Database database) {
        this.database = database;
    }

    public List<Product> getList() {
        return database.getProductList();
    }
}

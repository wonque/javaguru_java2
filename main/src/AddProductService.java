
public class AddProductService {

    Database database;

    public AddProductService(Database database){
        this.database = database;
    }

    public void add(String title, String description){
        Product newEntry = new Product();
        newEntry.setTitle(title);
        newEntry.setDescription(description);
        database.add(newEntry);
    }

}

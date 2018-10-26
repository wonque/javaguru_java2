import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryDataBase implements Database {

    List<Product> shopList = new ArrayList<>();

    @Override
    public void add(Product product) {
        shopList.add(product);

    }

    @Override
    public Optional<Product> findProductByTitle(String title) {
        return shopList.stream().filter(product -> product.getTitle().equals(title)).findFirst();
    }

    @Override
    public boolean remove(Product product) {
        return shopList.remove(product);
    }

    @Override
    public List<Product> getProductList() {
        // returns a copy of shopList
        return new ArrayList<>(shopList);
    }
}

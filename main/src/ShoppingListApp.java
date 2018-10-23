
import java.util.*;

public class ShoppingListApp {

    private List<Product> shopList;
    private String title;
    private Scanner scanner;
    private Map<Integer, Runnable> options;


    public ShoppingListApp(String title) {
        scanner = new Scanner(System.in);
        shopList = new ArrayList<>();
        this.title = title;
        options = new HashMap<>();
        generateOptions();
    }

    public List<Product> getShopList() {
        return shopList;
    }

    public String getTitle() {
        return title;
    }

    private void generateOptions() {
        options.put(1, this::addToShoppingList);
        options.put(2, this::removeFromShoppingList);
        options.put(3, this::printShoppingList);
    }

    private void addToShoppingList() {
        System.out.println("Type product title: ");
        String title = scanner.next();
        System.out.println("Type some description: ");
        String description = scanner.next();
        Product newEntry = new Product();
        newEntry.setTitle(title);
        newEntry.setDescription(description);
        shopList.add(newEntry);
        System.out.println("Product "+ title + " added!");
    }

    private void removeFromShoppingList() {
        System.out.println("To delete product enter products title: ");
        String productName = scanner.next();
        int itemsRemoved = 0;
        for (Product product : shopList) {
            if (product.getTitle().equals(productName)) {
                shopList.remove(product);
                itemsRemoved++;
            }
        }
        if (itemsRemoved == 0) {
            System.out.println("Product " + productName + " not found!\n");
        } else {
            System.out.println("Product " + productName + " removed!\n");
        }
    }

    private void printShoppingList() {
        for (Product product : shopList) {
            System.out.println(product);
        }
    }

    private boolean isUserAnswerValid(int ans) {
        return (ans >= 0 && ans <= 3);
    }

    private void printMenuOption() {
        System.out.println("Please, choose an option: \n");
        System.out.println("To add a product to list enter 1");
        System.out.println("To remove a product from current list enter 2");
        System.out.println("To print list enter 3");
        System.out.println("To terminate program enter 0");
        System.out.println();
    }

    public int getUserInput() throws InputMismatchException {
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

    public static void main(String[] args) {
        ShoppingListApp list1 = new ShoppingListApp("Some List");
        while (true) {
            int a = list1.getUserInput();
            if (list1.options.containsKey(a)) {
                list1.options.get(a).run();
            } else {
                break;
            }


        }

    }
}

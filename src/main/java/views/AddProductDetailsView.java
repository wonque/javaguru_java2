package views;

import domain.Product;
import services.AddProductDetailsService;

import java.util.Scanner;

public class AddProductDetailsView {

    private Scanner scanner;
    private AddProductDetailsService detailsService;
    private UserMenuView userMenu;

    public AddProductDetailsView(AddProductDetailsService detailsService) {
        this.detailsService = detailsService;
        this.userMenu = new UserMenuView();
        scanner = new Scanner(System.in);
    }

    public void setTitle(Product product) {
        String title = getProductTitleFromUser();
        detailsService.modifyProductTitle(title, product);
    }

    private void setDescription(Product product) {
        String description = getProductDescriptionFromUser();
        detailsService.modifyProductDescription(description, product);
    }

    private void setPrice(Product product) {
        double price = getProductPriceFromUser();
        detailsService.modifyProductPrice(price, product);
    }

    private void setCategory(Product product) {
        String category = getProductCategoryFromUser();
        detailsService.modifyProductCategory(category, product);
    }


    private String getProductTitleFromUser() {
        System.out.println("Type product title: ");
        return scanner.next();
    }

    private String getProductDescriptionFromUser() {
        System.out.println("Type product description: ");
        return scanner.next();
    }

    private String getProductCategoryFromUser() {
        System.out.println("Type product category: ");
        return scanner.next();
    }

    private double getProductPriceFromUser() {
        double input;
        do {
            System.out.println("Enter positive number for price: ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid input!");
                scanner.nextLine();
            }
            input = scanner.nextDouble();
        } while (input < 0);
        return input;
    }

    public void execute(Product product) {
        while (true) {
            userMenu.printProductDetailsSettingsMenu();
            int option = userMenu.getDetailsMenuItemToExecute();
            if (option == 0) {
                break;
            }
            switch (option) {
                case 1:
                    setDescription(product);
                    break;
                case 2:
                    setPrice(product);
                    break;
                case 3:
                    setCategory(product);
                    break;
            }

        }
    }
}


package views;

import domain.Product;
import services.ProductPriceService;
import services.SetProductDetailsService;

import java.math.BigDecimal;
import java.util.Scanner;

public class SetProductDetailsView {

    private SetProductDetailsService detailsService;
    private ProductPriceService priceService;
    private Scanner scanner = new Scanner(System.in);

    public SetProductDetailsView(SetProductDetailsService detailsService, ProductPriceService priceService) {
        this.detailsService = detailsService;
        this.priceService = priceService;
    }

    public String getProductDescriptionFromUser() {
        scanner.nextLine();
        System.out.println("Type product description: ");
        return scanner.nextLine();
    }

    public String getProductCategoryFromUser() {
        scanner.nextLine();
        System.out.println("Type product category: ");
        return scanner.nextLine();
    }

    public double getProductPriceFromUser() {
        double input;
        do {
            System.out.println("Enter positive number for price: ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid input!");
                scanner.nextLine();
            }
            input = scanner.nextDouble();
        } while (!priceService.isUserEnteredPriceBiggerThanZero(input));
        return input;
    }

    public int getFromUserDetailMenuItemToExecute() {
        int option;
        do {
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

    private boolean isUserInputValid(int input) {
        return (input >= 0 && input <= 3);
    }

    public void printAddDetailsSettingsMenu() {
        System.out.println("1: add product description");
        System.out.println("2: add product price");
        System.out.println("3: add product category");
        System.out.println("0: finish adding product");
        System.out.println();
    }


    public void execute(Product product) {
        while (true) {
            printAddDetailsSettingsMenu();
            int option = getFromUserDetailMenuItemToExecute();
            if (option == 0) {
                break;
            }
            switch (option) {
                case 1:
                    String description = getProductDescriptionFromUser();
                    detailsService.modifyProductDescription(description, product);
                    System.out.println("Product description is set to " + description + "\n");
                    break;
                case 2:
                    double priceFromUser = getProductPriceFromUser();
                    BigDecimal priceToSet = priceService.returnDecimalPrice(priceFromUser);
                    detailsService.modifyProductPrice(priceToSet, product);
                    System.out.println("Product price is set to " + priceFromUser + "\n");
                    break;
                case 3:
                    String category = getProductCategoryFromUser();
                    detailsService.modifyProductCategory(category, product);
                    System.out.println("Product category is set to " + category + "\n");
                    break;
            }
        }
    }
}


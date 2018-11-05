package views;

import domain.Product;
import services.AddProductService;
import services.ProductPriceService;
import services.SetProductDetailsService;


import java.util.Scanner;


public class AddProductView {

    private AddProductService addProductService;
    private Scanner scanner;
    private SetProductDetailsView setProductDetailsView;
    private SetProductDetailsService setProductDetailsService;
    private ProductPriceService priceService;


    public AddProductView(AddProductService addProductService) {
        this.priceService = new ProductPriceService();
        this.setProductDetailsService = new SetProductDetailsService();
        this.setProductDetailsView = new SetProductDetailsView(setProductDetailsService, priceService);
        this.addProductService = addProductService;
        this.scanner = new Scanner(System.in);
    }

    public void execute() {
        System.out.println("Add product process started.");
        String title = getProductTitleFromUser();
        Product newEntry = addProductService.createNewProduct(title);
        setProductDetailsView.execute(newEntry);
        addProductService.add(newEntry);
        System.out.println("Product " + newEntry.getTitle() + " added!\n");
    }

    private String getProductTitleFromUser() {
        String title;
        do {
            System.out.println("Type product title: ");
            title = scanner.nextLine();
        } while (addProductService.isProductTitleEmpty(title));
        return title;
    }

}

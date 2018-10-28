package views;

import services.AddProductService;

import java.util.Scanner;

public class AddProductView {

    private AddProductService addProductService;

    public AddProductView(AddProductService addProductService) {
        this.addProductService = addProductService;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type product title: ");
        String title = scanner.next();
        System.out.println("Type some description: ");
        String description = scanner.next();
        addProductService.add(title, description);
        System.out.println("Product " + title + " added!\n");
    }
}

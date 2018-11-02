package views;

import services.RemoveProductService;

import java.util.Scanner;

public class RemoveProductView {

    private RemoveProductService removeProductService;

    public RemoveProductView(RemoveProductService removeProductService) {
        this.removeProductService = removeProductService;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("To delete product enter product title: ");
        String productName = scanner.next();
        if (removeProductService.remove(productName)) {
            System.out.println("Product " + productName + " removed!\n");
        } else {
            System.out.println("Product " + productName + " not found!\n");
        }
    }
}
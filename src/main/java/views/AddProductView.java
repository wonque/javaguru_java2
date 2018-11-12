package views;

import services.AddProductService;


public class AddProductView {

    private AddProductService addProductService;

    public AddProductView(AddProductService addProductService) {
        this.addProductService = addProductService;
    }

    public void execute(String title) {
        System.out.println("Add product process started.");
        addProductService.add(title);
//        details.execute(title);
//        setProductDetailsView.execute(newEntry);
        System.out.println("Product " + title + " added!\n");
    }
}

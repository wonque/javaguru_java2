package views;

import domain.Product;
import services.AddProductService;


public class AddProductView {

    private AddProductService addProductService;
    private AddProductDetailsView addProductDetailsView;

    public AddProductView(AddProductService addProductService, AddProductDetailsView detailsView) {
        this.addProductService = addProductService;
        this.addProductDetailsView = detailsView;
    }

    public void execute() {
        Product newEntry = addProductService.createNewProduct();
        addProductDetailsView.setTitle(newEntry);
        addProductDetailsView.execute(newEntry);
        addProductService.add(newEntry);
        System.out.println("Product " + newEntry.getTitle() + " added!\n");
    }

}

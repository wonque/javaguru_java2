package lv.java2.shopping_list.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lv.java2.shopping_list.services.add.AddProductRequest;
import lv.java2.shopping_list.services.add.AddProductResponse;
import lv.java2.shopping_list.services.add.AddProductService;

@Component
public class AddProductView {

    @Autowired
    private AddProductService addProductService;

    @Autowired
    private UserInputGetters inputGetters;

    public void execute() {
        System.out.println("Add product process started.");
        String title = inputGetters.getProductTitleFromUser();
        AddProductRequest newRequest = new AddProductRequest(title);
        AddProductResponse response = addProductService.add(newRequest);
        if (response.isSuccess()) {
            System.out.println("Product " + title + " added!\n");

        } else {
            response.displayErrors();
        }
    }
}

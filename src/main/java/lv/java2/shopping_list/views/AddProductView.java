package lv.java2.shopping_list.views;

import lv.java2.shopping_list.services.add.product.ProductAdditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lv.java2.shopping_list.services.add.product.ProductAdditionRequest;
import lv.java2.shopping_list.services.add.product.ProductAdditionResponse;

//@Component
public class AddProductView {

    @Autowired
    private ProductAdditionService productAdditionService;

    @Autowired
    private UserInputGetters inputGetters;

    public Long execute() {
//        System.out.println("Add product process started.");
        String title = inputGetters.getProductTitleFromUser();
        ProductAdditionRequest newRequest = new ProductAdditionRequest(title);
        ProductAdditionResponse response = productAdditionService.add(newRequest);
        if (response.isSuccess()) {
            System.out.println("Product " + title + " added!\n");
        } else {
            response.displayErrors();
        }
        return response.getId();
    }
}

package lv.java2.shopping_list.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lv.java2.shopping_list.services.remove.RemoveProductRequest;
import lv.java2.shopping_list.services.remove.RemoveProductResponse;
import lv.java2.shopping_list.services.remove.RemoveProductService;

@Component
public class RemoveProductView {

    @Autowired
    private RemoveProductService removeProductService;

    @Autowired
    private UserInputGetters inputGetters;

    public void execute() {
        String productName = inputGetters.getProductTitleFromUser();
        RemoveProductRequest request = new RemoveProductRequest(productName);
        RemoveProductResponse response = removeProductService.remove(request);
        if (response.isSuccess()) {
            System.out.println("Product " + productName + " removed!\n");
        } else {
            System.out.println("Product " + productName + " not removed!\n");
            response.displayErrors();
        }
    }
}
package lv.java2.shopping_list.views;

import lv.java2.shopping_list.services.product.adition.ProductAdditionService;
import org.springframework.beans.factory.annotation.Autowired;
import lv.java2.shopping_list.services.product.adition.ProductAdditionRequest;
import lv.java2.shopping_list.services.product.adition.ProductAdditionResponse;

//@Component
public class AddProductView {

    @Autowired
    private ProductAdditionService productAdditionService;

    @Autowired
    private UserInputGetter inputGetters;

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

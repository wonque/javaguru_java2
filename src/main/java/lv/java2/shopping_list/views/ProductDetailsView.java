package lv.java2.shopping_list.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lv.java2.shopping_list.services.ProductDescriptionService;
import lv.java2.shopping_list.services.ProductPriceService;

//@Component
public class ProductDetailsView {

    @Autowired
    private ProductDescriptionService descriptionService;
    @Autowired
    private ProductPriceService priceService;
    @Autowired
    private UserInputGetters inputGetter;


    private void printDetailsSettingsMenu() {
        System.out.println("1: add product description");
        System.out.println("2: add product price");
        System.out.println("3: add product category");
        System.out.println("0: finish\n");
        System.out.println();
    }
//
//    private void updateDescription(String productTitle) {
//        String description = inputGetter.getProductDescriptionFromUser();
//        if (descriptionService.update(productTitle, description)) {
//            System.out.println(productTitle + " description is set to " + description);
//        } else {
//            System.out.println("Product " + productTitle + " not found!");
//        }
//    }

//    private void updatePrice(String productTitle) {
//        double price = inputGetter.getProductPriceFromUser();
//        if (priceService.update(productTitle, price)) {
//            System.out.println(productTitle + " price is set to " + price);
//        } else {
//            System.out.println("Product " + productTitle + " not found!");
//        }
//
//    }

//    public void execute(String title) {
//        while (true) {
//            printDetailsSettingsMenu();
//            int option = inputGetter.getFromUserDetailMenuItemToExecute();
//            if (option == 0) {
//                break;
//            }
//            switch (option) {
//                case 1:
//                    updateDescription(title);
//                    break;
//                case 2:
//                    updatePrice(title);
//                    break;
//            }
//        }
//    }
}

//TODO AddProductView->ProductAdditionService(Product Added)->SetDetailsView->(DescriptionService+PriceService+CategoryService)->
//TODO -> DataBase UPDATE request->AdditionResponse to User;
//ProductAdditionService adding product to DB with title and null values for other fields
//Than SetProductDetailsView is called
//DetailService will UPDATE either description, price or category and save it to base;
//



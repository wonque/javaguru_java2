package lv.java2.shopping_list.services.add.product;

import lv.java2.shopping_list.domain.Product;
import lv.java2.shopping_list.services.Error;

import java.util.List;

public class ProductAdditionResponse {

    private Product addedProduct;
    private List<Error> errors;


    public ProductAdditionResponse(Product addedProduct) {
        this.addedProduct = addedProduct;
    }

    public ProductAdditionResponse(List<Error> errors) {
        this.errors = errors;
    }

    public boolean isSuccess() {
        return (addedProduct != null && errors == null || errors.isEmpty());
    }

    public Product getAddedProduct() {
        return addedProduct;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public void displayErrors() {
        errors.forEach(error -> System.out.println(error.getErrorDescription()));
    }

}

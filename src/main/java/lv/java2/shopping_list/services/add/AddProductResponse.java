package lv.java2.shopping_list.services.add;

import lv.java2.shopping_list.services.Error;

import java.util.List;

public class AddProductResponse {

    private Long productId;
    private List<Error> errors;

    public AddProductResponse(Long productId) {
        this.productId = productId;
    }

    public AddProductResponse(List<Error> errors) {
        this.errors = errors;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public boolean isSuccess() {
        return productId != null && (errors == null || errors.isEmpty());
    }

    public void displayErrors() {
        errors.forEach(error -> System.out.println(error.getErrorDescription()));
    }
}

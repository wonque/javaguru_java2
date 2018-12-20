package lv.java2.shopping_list.services.product.adition;

import lv.java2.shopping_list.services.ShoppingListError;

import java.util.List;

public class ProductAdditionResponse {

    private Long id;
    private List<ShoppingListError> errors;


    public ProductAdditionResponse(Long id) {
        this.id = id;
    }

    public ProductAdditionResponse(List<ShoppingListError> errors) {
        this.errors = errors;
    }

    public boolean isSuccess() {
        return (id != null && errors == null || errors.isEmpty());
    }

    public Long getId() {
        return id;
    }

    public List<ShoppingListError> getErrors() {
        return errors;
    }

    public void setErrors(List<ShoppingListError> errors) {
        this.errors = errors;
    }

    public void displayErrors() {
        errors.forEach(error -> System.out.println(error.getErrorDescription()));
    }

}

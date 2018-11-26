package lv.java2.shopping_list.services;

import java.util.List;

public class AddShoppingListResponse {

    private Long listId;
    private List<Error> errors;

    public AddShoppingListResponse(Long listId) {
        this.listId = listId;
    }

    public AddShoppingListResponse(List<Error> errors) {
        this.errors = errors;
    }

    public boolean isSuccess() {
        return (listId != null && errors == null || errors.isEmpty());
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
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

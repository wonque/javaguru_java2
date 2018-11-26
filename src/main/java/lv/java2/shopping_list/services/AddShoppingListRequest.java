package lv.java2.shopping_list.services;

public class AddShoppingListRequest {

    private String title;

    public AddShoppingListRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

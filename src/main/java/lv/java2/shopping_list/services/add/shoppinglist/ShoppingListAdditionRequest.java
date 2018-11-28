package lv.java2.shopping_list.services.add.shoppinglist;


public class ShoppingListAdditionRequest {

    private String title;

    public ShoppingListAdditionRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}

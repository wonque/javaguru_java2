package lv.java2.shopping_list.services.remove.shoppinglist;

public class RemoveShoppingListRequest {

    private String title;

    public RemoveShoppingListRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

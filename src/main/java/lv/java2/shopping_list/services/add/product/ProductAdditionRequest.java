package lv.java2.shopping_list.services.add.product;


public class ProductAdditionRequest {

    private String title;

    public ProductAdditionRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}

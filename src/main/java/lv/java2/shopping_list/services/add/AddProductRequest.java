package lv.java2.shopping_list.services.add;



public class AddProductRequest {

    private String title;

    public AddProductRequest(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

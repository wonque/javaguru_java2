package services.remove;

public class RemoveProductRequest {

    private String title;

    public RemoveProductRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

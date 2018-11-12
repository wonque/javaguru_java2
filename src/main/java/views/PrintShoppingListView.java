package views;

import org.springframework.stereotype.Component;
import services.GetShoppingListService;

@Component
public class PrintShoppingListView {

    private GetShoppingListService getListService;

    public PrintShoppingListView(GetShoppingListService getListService) {
        this.getListService = getListService;
    }

    public void execute() {
        if (getListService.getList().size() > 0) {
            getListService.getList().forEach(System.out::println);
        } else {
            System.out.println("Shopping List is Empty!\n");
        }
    }
}

package lv.java2.shopping_list.web.exceptions;

public class ArchivedShoppingListException extends RuntimeException {

    public ArchivedShoppingListException(String message) {
        super(message);
    }

    public ArchivedShoppingListException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArchivedShoppingListException(Throwable cause) {
        super(cause);
    }
}

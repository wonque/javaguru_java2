package lv.java2.shopping_list.web.exceptions;

public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String message) {
        super(message);
    }

    public DuplicateResourceException(Throwable cause) {
        super(cause);
    }

    public DuplicateResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}

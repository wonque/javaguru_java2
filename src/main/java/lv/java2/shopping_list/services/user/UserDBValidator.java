package lv.java2.shopping_list.services.user;

public interface UserDBValidator {

    boolean isUserLoginExists(String login);

    boolean isUserIdExists(Long userId);

}

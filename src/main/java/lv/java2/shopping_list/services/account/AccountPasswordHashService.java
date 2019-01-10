package lv.java2.shopping_list.services.account;


public interface AccountPasswordHashService {

    String hashPassword(String plainTextPassword);

    boolean matchPasswords(String hashPassword, String plainTextPassword);

}

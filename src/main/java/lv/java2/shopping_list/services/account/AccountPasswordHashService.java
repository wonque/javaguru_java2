package lv.java2.shopping_list.services.account;

import lv.java2.shopping_list.services.account.get.GetAccountRequest;

public interface AccountPasswordHashService {

    String hashPassword(String plainTextPassword);

    boolean matchPasswords(String hashPassword, String plainTextPassword);

}

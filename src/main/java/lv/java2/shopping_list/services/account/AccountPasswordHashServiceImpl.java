package lv.java2.shopping_list.services.account;

import lv.java2.shopping_list.services.account.get.GetAccountRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class AccountPasswordHashServiceImpl implements AccountPasswordHashService {

    @Override
    public String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    @Override
    public boolean matchPasswords(String hashPassword, String plainTextPassword) {
        return BCrypt.checkpw(plainTextPassword, hashPassword);
    }
}

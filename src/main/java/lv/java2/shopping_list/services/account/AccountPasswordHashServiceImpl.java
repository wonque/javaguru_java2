package lv.java2.shopping_list.services.account;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
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

package lv.java2.shopping_list.account.services.password;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;


@Service
public class PasswordService {

    public String processPassword(String plaintTextPassword) {
        return BCrypt.hashpw(plaintTextPassword, BCrypt.gensalt());
    }

    public boolean validatePasswords(String userEnteredPassword, String storedPassword) {
        return BCrypt.checkpw(userEnteredPassword, storedPassword);
    }
}

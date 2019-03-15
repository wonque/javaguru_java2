package lv.java2.shopping_list.services.user.registration;

import lv.java2.shopping_list.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationValidator {

    @Autowired
    private UserRepository repository;

    public boolean isLoginExists(String emailAsLogin) {
        return repository.findByEmail(emailAsLogin).isPresent();
    }
}

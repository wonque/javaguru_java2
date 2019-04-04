package lv.java2.shopping_list.services.user;

import lv.java2.shopping_list.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDBValidatorImpl implements UserDBValidator {

    @Autowired
    private UserRepository repository;

    @Override
    public boolean isUserLoginExists(String emailAsLogin) {
        return repository.findByEmail(emailAsLogin).isPresent();
    }

    @Override
    public boolean isUserIdExists(Long userId) {
        return repository.findById(userId).isPresent();
    }
}

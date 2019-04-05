package lv.java2.shopping_list.services.user.registration;

import lv.java2.shopping_list.domain.User;
import lv.java2.shopping_list.repository.UserRepository;
import lv.java2.shopping_list.services.user.UserDBValidator;
import lv.java2.shopping_list.web.dto.UserDTO;
import lv.java2.shopping_list.web.dto.mappers.UserMapper;
import lv.java2.shopping_list.web.exceptions.DuplicateResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final UserRepository repository;
    private final UserMapper userMapper;
    private final UserDBValidator validator;

    @Autowired
    public UserRegistrationServiceImpl(UserRepository repository, UserMapper userMapper, UserDBValidator validator) {
        this.repository = repository;
        this.userMapper = userMapper;
        this.validator = validator;
    }


    @Transactional
    public UserDTO register(UserDTO userDTO) {
        if (validator.isUserLoginExists(userDTO.getEmail())) {
            throw new DuplicateResourceException("User with " + userDTO.getEmail() + " already registered!");
        }
        String hashedPass = hashPassword(userDTO.getPassword());
        User user = userMapper.toDomain(userDTO);
        user.setPassword(hashedPass);
        repository.save(user);
        userDTO.setUserId(user.getId());
        return userDTO;
    }

    private String hashPassword(String userPassword) {
        return BCrypt.hashpw(userPassword, BCrypt.gensalt());
    }

}

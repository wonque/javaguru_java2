package lv.java2.shopping_list.user.services.registration;

import lv.java2.shopping_list.ServiceResponse;
import lv.java2.shopping_list.user.domain.User;
import lv.java2.shopping_list.user.repository.UserRepository;
import lv.java2.shopping_list.web.dto.UserDTO;
import lv.java2.shopping_list.web.dto.mappers.UserMapper;
import lv.java2.shopping_list.web.exceptions.DuplicateResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRegistrationValidator validator;


    @Transactional
    public ServiceResponse<UserDTO> register(UserDTO userDTO) {
        validateDuplicateLogin(userDTO);
        String hashedPass = hashPassword(userDTO.getPassword());
        User user = userMapper.toDomain(userDTO);
        user.setPassword(hashedPass);
        repository.save(user);
        userDTO.setUserId(user.getId());
        return new ServiceResponse<>(userDTO);
    }

    private String hashPassword(String userPassword) {
        return BCrypt.hashpw(userPassword, BCrypt.gensalt());
    }

    private void validateDuplicateLogin(UserDTO userDTO) {
        if (validator.isLoginExists(userDTO.getEmail())) {
            throw new DuplicateResourceException("User with " + userDTO.getEmail() + " already registered!");
        }
    }

}

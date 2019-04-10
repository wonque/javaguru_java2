package lv.java2.shopping_list.services.user.registration;

import lv.java2.shopping_list.domain.User;
import lv.java2.shopping_list.repository.UserRepository;
import lv.java2.shopping_list.services.user.validation.UserValidationService;
import lv.java2.shopping_list.web.dto.UserDTO;
import lv.java2.shopping_list.web.dto.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final UserRepository repository;
    private final UserMapper userMapper;
    private final UserValidationService validator;

    @Autowired
    public UserRegistrationServiceImpl(UserRepository repository,
                                       UserMapper userMapper,
                                       UserValidationService validator) {
        this.repository = repository;
        this.userMapper = userMapper;
        this.validator = validator;
    }


    @Transactional
    public UserDTO register(UserDTO userDTO) {
        validator.validate(userDTO);

        String hashedPass = hashPassword(userDTO.getPassword());

        User user = userMapper.toDomain(userDTO);
        user.setPassword(hashedPass);

        repository.save(user);

        return userMapper.toDTO(user);
    }

    private String hashPassword(String userPassword) {
        return BCrypt.hashpw(userPassword, BCrypt.gensalt());
    }

}

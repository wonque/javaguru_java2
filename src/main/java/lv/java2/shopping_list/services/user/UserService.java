package lv.java2.shopping_list.services.user;

import lv.java2.shopping_list.domain.User;
import lv.java2.shopping_list.dto.UserDTO;
import lv.java2.shopping_list.dto.mappers.UserMapper;
import lv.java2.shopping_list.repository.UserRepository;
import lv.java2.shopping_list.services.user.validation.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;
    private final UserValidationService validator;

    @Autowired
    public UserService(UserRepository repository, UserMapper userMapper, UserValidationService validator) {
        this.repository = repository;
        this.userMapper = userMapper;
        this.validator = validator;
    }

    public UserDTO register(UserDTO userDTO) {
        validator.validate(userDTO);

        User user = userMapper.toDomain(userDTO);
        user.setPassword(UserService.PasswordService.encodePass(userDTO));

        repository.save(user);
        return userMapper.toDTO(user);
    }

    public UserDTO findById(Long userId) {
        Optional<User> foundedUser = repository.findById(userId);
        return foundedUser.map(userMapper::toDTO)
                .orElseThrow(() ->
                        new EntityNotFoundException("User not found, id: " + userId));
    }

    private static class PasswordService {

        private static String encodePass(UserDTO userDTO) {
            return BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt());
        }
    }

}

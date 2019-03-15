package lv.java2.shopping_list.services.user.get;

import lv.java2.shopping_list.domain.User;
import lv.java2.shopping_list.repository.UserRepository;
import lv.java2.shopping_list.web.dto.UserDTO;
import lv.java2.shopping_list.web.dto.mappers.UserMapper;
import lv.java2.shopping_list.web.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.Optional;


@Service
public class GetUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Transactional
    public UserDTO findById(Long id) {
        Optional<User> founded = userRepository.findById(id);
        return founded.map(this::buildUserFoundedResponse)
                .orElseThrow(() -> new ResourceNotFoundException(
                        MessageFormat.format("User with ID = {0} not found!", id)));
    }

    private UserDTO buildUserFoundedResponse(User user) {
        return userMapper.toDTO(user);
    }

}

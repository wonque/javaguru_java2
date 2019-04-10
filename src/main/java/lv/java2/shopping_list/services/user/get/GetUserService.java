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

    private final UserRepository repository;
    private final UserMapper userMapper;

    @Autowired
    public GetUserService(UserRepository repository, UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserDTO findById(Long id) {
        Optional<User> founded = repository.findById(id);
        return founded.map(userMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException(
                        MessageFormat.format("User with ID = {0} not found!", id)));
    }

}

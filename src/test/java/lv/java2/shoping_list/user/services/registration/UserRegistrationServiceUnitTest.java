package lv.java2.shoping_list.user.services.registration;


import lv.java2.shopping_list.ServiceResponse;
import lv.java2.shopping_list.domain.User;
import lv.java2.shopping_list.repository.UserRepository;
import lv.java2.shopping_list.services.user.registration.UserRegistrationServiceImpl;
import lv.java2.shopping_list.services.user.registration.UserRegistrationValidator;
import lv.java2.shopping_list.web.dto.UserDTO;
import lv.java2.shopping_list.web.dto.mappers.UserMapper;
import lv.java2.shopping_list.web.exceptions.DuplicateResourceException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNull;


@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationServiceUnitTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserRegistrationValidator validator;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private UserDTO userDTO;

    @InjectMocks
    private UserRegistrationServiceImpl registrationService = new UserRegistrationServiceImpl();

    @Before
    public void init() {
        this.userDTO = new UserDTO("login", "password1", "vasya");
    }


    @Test
    public void returnResponseWithRegisteredAccount() {
        User user = Mockito.mock(User.class);
        Mockito.when(validator.isLoginExists(userDTO.getEmail())).thenReturn(false);
        Mockito.when(userMapper.toDomain(userDTO)).thenReturn(user);
        Mockito.when(repository.save(user)).thenReturn(user);
        ServiceResponse<UserDTO> response = registrationService.register(userDTO);
        assertNotNull(response.getData().getUserId());
    }

    @Test
    public void throwsExceptionIfUserRegistered() throws DuplicateResourceException {
        exception.expect(DuplicateResourceException.class);
        exception.expectMessage("User with login already registered!");
        Mockito.when(validator.isLoginExists(userDTO.getEmail())).thenReturn(true);
        ServiceResponse<UserDTO> response = registrationService.register(userDTO);
        assertNull(response.getData());
    }

}



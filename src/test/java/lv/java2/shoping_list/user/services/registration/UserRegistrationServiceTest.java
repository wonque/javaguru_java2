package lv.java2.shoping_list.user.services.registration;


import lv.java2.shopping_list.domain.User;
import lv.java2.shopping_list.repository.UserRepository;
import lv.java2.shopping_list.services.user.registration.UserRegistrationServiceImpl;
import lv.java2.shopping_list.services.user.validation.UserValidationService;
import lv.java2.shopping_list.web.dto.UserDTO;
import lv.java2.shopping_list.web.dto.mappers.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserValidationService validator;

    private UserDTO userDTO;
    private User user;

    /*
    Used to capture the argument and check values
     */
    @Captor
    private ArgumentCaptor<UserDTO> userDtoCapture;

    @InjectMocks
    private UserRegistrationServiceImpl registrationService;

    @Before
    public void init() {
        this.userDTO = new UserDTO("login", "password1", "vasya");
        userDTO.setUserId(1L);
        this.user = new User("login", "password1", "vasya");
        user.setId(1L);
    }

    @Test
    public void returnResponseWithRegisteredAccount() {

        when(userMapper.toDomain(userDTO)).thenReturn(user);
        when(repository.save(user)).thenReturn(user);
        when(userMapper.toDTO(user)).thenReturn(userDTO);

        UserDTO response = registrationService.register(userDTO);

        verify(validator).validate(userDtoCapture.capture()); //Capturing the argument that was submitted to validator
        UserDTO captorResult = userDtoCapture.getValue();

        assertNotNull(response.getUserId());
        assertThat(response).isEqualTo(userDTO);
        assertThat(captorResult).isEqualTo(userDTO);   //checking if the captured argument is the same
    }

}



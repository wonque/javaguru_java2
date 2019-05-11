package lv.java2.shoping_list.services.user;

import lv.java2.shopping_list.domain.User;
import lv.java2.shopping_list.dto.UserDTO;
import lv.java2.shopping_list.dto.mappers.UserMapper;
import lv.java2.shopping_list.repository.UserRepository;
import lv.java2.shopping_list.services.user.UserService;
import lv.java2.shopping_list.services.user.validation.UserValidationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    public UserRepository repository;

    @Mock
    public UserMapper mapper;

    @Mock
    public UserValidationService validator;

    @Captor
    ArgumentCaptor<UserDTO> userCaptor;

    private UserService testObject;

    @Before
    public void setup() {
        testObject = new UserService(repository, mapper, validator);
    }

    @Test
    public void shouldRegister() {
        UserDTO userDTO = userDTO();
        User user = user();

        when(mapper.toDomain(userDTO)).thenReturn(user);
        when(repository.save(user)).thenReturn(user);
        when(mapper.toDTO(user)).thenReturn(userDTO);

        UserDTO response = testObject.register(userDTO);

        assertThat(response.getUserId()).isEqualTo(1001L);
    }

    @Test
    public void shouldValidateUserDTOArgument() {
        UserDTO userDTO = userDTO();
        User user = user();

        when(mapper.toDomain(userDTO)).thenReturn(user);
        when(repository.save(user)).thenReturn(user);
        when(mapper.toDTO(user)).thenReturn(userDTO);

        testObject.register(userDTO);

        verify(validator).validate(userCaptor.capture());
        UserDTO captorResult = userCaptor.getValue();

        assertThat(captorResult).isEqualTo(userDTO);
    }

    @Test
    public void shouldFindUserById() {

        when(repository.findById(1001L)).thenReturn(Optional.ofNullable(user()));
        when(mapper.toDTO(user())).thenReturn(userDTO());

        UserDTO response = testObject.findById(1001L);

        assertThat(response).isEqualTo(userDTO());
    }

    @Test
    public void shouldThrowExceptionUserNotFound() {

        when(repository.findById(1001L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> testObject.findById(1001L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("User not found, id: 1001");
    }

    private UserDTO userDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("TEST@EMAIL.COM");
        userDTO.setPassword("TESTPASSWORD123");
        userDTO.setUserId(1001L);
        return userDTO;
    }

    private User user() {
        User user = new User();
        user.setEmail("TEST@EMAIL.COM");
        user.setPassword("TESTPASSWORD123");
        user.setId(1001L);
        return user;
    }


}

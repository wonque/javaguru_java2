package lv.java2.shoping_list.services.user.get;

import lv.java2.shopping_list.domain.User;
import lv.java2.shopping_list.repository.UserRepository;
import lv.java2.shopping_list.services.user.get.GetUserService;
import lv.java2.shopping_list.web.dto.UserDTO;
import lv.java2.shopping_list.web.dto.mappers.UserMapper;
import lv.java2.shopping_list.web.exceptions.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetUserServiceTest {

    @Mock
    private UserRepository repository;
    @Mock
    private UserMapper userMapper;

    private UserDTO userDTO;
    private User user;

    @Before
    public void setup() {
        this.userDTO = userDTO();
        this.user = user();
    }

    @InjectMocks
    private GetUserService getUserService;


    private Long id = 1L;

    @Test
    public void throwsExceptionIfUserNotFound() {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> getUserService.findById(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("User with ID = 1 not found!");

    }

    @Test
    public void returnResponseWithUser() {
        when(repository.findById(userDTO.getUserId())).thenReturn(Optional.of(user));
        when(userMapper.toDTO(user)).thenReturn(userDTO);

        UserDTO response = getUserService.findById(userDTO.getUserId());

        assertNotNull(response.getUserId());
        assertThat(response).isEqualTo(userDTO);
        assertEquals((long) response.getUserId(), 111L);
    }

    private UserDTO userDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(111L);
        userDTO.setEmail("TEST_EMAIL@EMAIL.TEST");
        userDTO.setUsername("TEST_USERNAME");
        return userDTO;
    }

    private User user() {
        User user = new User();
        user.setId(111L);
        user.setEmail("TEST_EMAIL@EMAIL.TEST");
        user.setUsername("TEST_USERNAME");
        return user;

    }

}

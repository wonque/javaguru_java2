package lv.java2.shoping_list.user.services.get;

import lv.java2.shopping_list.domain.User;
import lv.java2.shopping_list.repository.UserRepository;
import lv.java2.shopping_list.services.user.get.GetUserService;
import lv.java2.shopping_list.web.dto.UserDTO;
import lv.java2.shopping_list.web.dto.mappers.UserMapper;
import lv.java2.shopping_list.web.exceptions.ResourceNotFoundException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetUserServiceTest {

    @Mock
    private UserRepository repository;
    @Mock
    private UserMapper userMapper;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @InjectMocks
    private GetUserService getUserService;


    private Long id = 1L;

    @Test
    public void throwsExceptionIfUserNotFound() throws ResourceNotFoundException {
        when(repository.findById(id)).thenReturn(Optional.empty());
        expectedException.expect(ResourceNotFoundException.class);
        expectedException.expectMessage("User with ID = 1 not found!");
        getUserService.findById(id);

    }

    @Test
    public void returnResponseWithUser(){
        User user = Mockito.mock(User.class);
        UserDTO userDTO = Mockito.mock(UserDTO.class);
        when(repository.findById(id)).thenReturn(Optional.of(user));
        when(userMapper.toDTO(user)).thenReturn(userDTO);
        UserDTO response = getUserService.findById(id);
        assertNotNull(response.getUserId());
    }
}

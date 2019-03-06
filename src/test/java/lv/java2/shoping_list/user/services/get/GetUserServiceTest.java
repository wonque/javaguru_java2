package lv.java2.shoping_list.user.services.get;

import lv.java2.shopping_list.ServiceResponse;
import lv.java2.shopping_list.user.domain.User;
import lv.java2.shopping_list.user.repository.UserRepository;
import lv.java2.shopping_list.user.services.get.GetUserService;
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
    private GetUserService getUserService = new GetUserService();


    private Long id = 1L;

    @Test
    public void throwsExceptionIfUserNotFound() {
        when(repository.findById(id)).thenReturn(Optional.empty());
        expectedException.expect(ResourceNotFoundException.class);
        expectedException.expectMessage("User with ID = 1 not found!");
        ServiceResponse response = getUserService.findById(id);

    }

    @Test
    public void returnResponseWithUser(){
        User user = Mockito.mock(User.class);
        UserDTO userDTO = Mockito.mock(UserDTO.class);
        when(repository.findById(id)).thenReturn(Optional.of(user));
        when(userMapper.toDTO(user)).thenReturn(userDTO);
        ServiceResponse response = getUserService.findById(id);
        assertNotNull(response.getData());
    }
}

package lv.java2.shopping_list.services.user.validation;

import lv.java2.shopping_list.domain.User;
import lv.java2.shopping_list.dto.UserDTO;
import lv.java2.shopping_list.exceptions.DuplicateResourceException;
import lv.java2.shopping_list.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UniqueEmailRuleTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    public UniqueEmailRule testObject;


    @Test
    public void shouldThrowExceptionDuplicateEmail() {
        when(repository.findByEmail(userDTO().getEmail())).thenReturn(Optional.of(user()));

        assertThatThrownBy(() -> testObject.validate(userDTO()))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessage("User with TEST@EMAIL.COM already registered!");
    }

    @Test
    public void shouldValidate() {
        when(repository.findByEmail(userDTO().getEmail())).thenReturn(Optional.empty());

        testObject.validate(userDTO());
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

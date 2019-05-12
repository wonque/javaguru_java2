package lv.java2.shopping_list.services.user.validation;

import lv.java2.shopping_list.domain.User;
import lv.java2.shopping_list.dto.UserDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class UserValidationServiceTest {

    @Mock
    public UniqueEmailRule uniqueEmailRule;

    public UserValidationService testObject;

    @Captor
    ArgumentCaptor<UserDTO> dtoArgumentCaptor;

    @Before
    public void setup() {
        Set<UserValidationRule> rules = new HashSet<>();
        rules.add(uniqueEmailRule);
        testObject = new UserValidationService(rules);
    }

    @Test
    public void shouldValidate() {
        testObject.validate(userDTO());

        Mockito.verify(uniqueEmailRule).validate(dtoArgumentCaptor.capture());
        UserDTO capResult = dtoArgumentCaptor.getValue();

        assertThat(capResult).isEqualTo(userDTO());
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

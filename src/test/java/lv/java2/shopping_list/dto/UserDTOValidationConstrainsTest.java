package lv.java2.shopping_list.dto;


import lv.java2.shopping_list.controllers.validation.CreateEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserDTOValidationConstrainsTest {

    private Validator validator;
    private UserDTO userDTO = userDTO();

    @Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

    }

    @Test
    public void nullEmailShouldFail() {
        userDTO.setEmail(null);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO, CreateEntity.class);

        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void blankEmailShouldFail() {
        userDTO.setEmail("");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO, CreateEntity.class);

        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void emptyStringEmailShouldFail() {
        userDTO.setEmail(" ");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO, CreateEntity.class);

        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void invalidEmailShouldFail() {
        userDTO.setEmail("someemail@.");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO, CreateEntity.class);

        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void validEmailShouldPass() {
        userDTO.setEmail("TEST@EMAIL.COM");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        assertThat(violations.size()).isEqualTo(0);

    }

    @Test
    public void nullPasswordShouldFail() {
        userDTO.setPassword(null);
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO, CreateEntity.class);

        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void emptyPasswordShouldFail() {
        userDTO.setPassword("");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO, CreateEntity.class);

        assertThat(violations.size()).isEqualTo(2);
    }

    @Test
    public void lessThanSixSymbolsPassShouldFail() {
        userDTO.setPassword("one");
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO, CreateEntity.class);

        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void validPasswordShouldPass() {
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO, CreateEntity.class);

        assertThat(violations.size()).isEqualTo(0);
    }

    private UserDTO userDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("TEST@EMAIL.COM");
        userDTO.setPassword("TESTPASSWORD123");
        return userDTO;
    }


}

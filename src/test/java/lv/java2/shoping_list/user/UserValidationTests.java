package lv.java2.shoping_list.user;

import lv.java2.shopping_list.web.dto.UserDTO;
import lv.java2.shopping_list.web.dto.validation.NewEntry;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static junit.framework.TestCase.*;

public class UserValidationTests {

    private Validator validator;
    private UserDTO userDTO;
    private Set<ConstraintViolation<UserDTO>> violations;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        userDTO = new UserDTO("email@email.email", "password123", "Lincoln");
    }

    @Test
    public void invalidEmailShouldFail() {
        userDTO.setEmail("email");
        violations = validator.validate(userDTO);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("Please provide a valid email address", violations.iterator().next().getMessage());
    }

    @Test
    public void nullEmailShouldFail() {
        userDTO.setEmail(null);
        violations = validator.validate(userDTO);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("This field cannot be blank", violations.iterator().next().getMessage());
    }

    @Test
    public void whitespaceEmailShouldFail() {
        userDTO.setEmail("    ");
        violations = validator.validate(userDTO);
        assertFalse(violations.isEmpty());
        assertEquals(2, violations.size());
    }

    @Test
    public void invalidPasswordShouldFail() {
        userDTO.setPassword("inv");
        violations = validator.validate(userDTO);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("Password must be longer than six symbols",
                violations.iterator().next().getMessage());
    }

    @Test
    public void nullPasswordShouldFail() {
        userDTO.setPassword(null);
        violations = validator.validate(userDTO);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("This field cannot be blank", violations.iterator().next().getMessage());
    }

    @Test
    public void whitespacePasswordShouldFail() {
        userDTO.setPassword("      ");
        violations = validator.validate(userDTO);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("This field cannot be blank", violations.iterator().next().getMessage());
    }

    @Test
    public void validEmailAndPasswordShouldPass() {
        violations = validator.validate(userDTO);
        assertTrue(violations.isEmpty());
    }

}

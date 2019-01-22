package account.services.password;

import lv.java2.shopping_list.account.services.password.PasswordService;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class PasswordServiceTest {

    private PasswordService passwordService = new PasswordService();
    private String plaintTextPasswor = "password";
    private String processedPassword;

    @Test
    public void processPasswordTest() {
        processedPassword = passwordService.processPassword(plaintTextPasswor);
        assertNotNull(processedPassword);
        assertNotEquals(plaintTextPasswor, processedPassword);
    }

}

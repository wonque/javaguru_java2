package services.account.registration;

import lv.java2.shopping_list.services.account.AccountPasswordHashService;
import lv.java2.shopping_list.services.account.AccountPasswordHashServiceImpl;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AccountPasswordHashServiceTest {

    private AccountPasswordHashService passwordHashService = new AccountPasswordHashServiceImpl();

    @Test
    public void test() {
        String hashPass = passwordHashService.hashPassword("password");
        assertNotNull(hashPass);
        assertEquals(hashPass.getClass(), String.class);
    }
}

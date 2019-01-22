package db;


import lv.java2.shopping_list.ShoppingListApp;
import lv.java2.shopping_list.account.repository.AccountRepository;
import lv.java2.shopping_list.account.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Optional;

import static junit.framework.TestCase.*;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest (classes = ShoppingListApp.class)
public class AccountRepositoryIntegrationTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testAtrueIfAdded() {
        Account account = new Account();
        account.setLogin("poooooo@foo.bar");
        account.setPassword("123456");
        assertNull(account.getId());
        accountRepository.save(account);
        assertNotNull(account.getId());
    }


    @Test
    public void testA_falseIfLoginDoesNotExists() {
        Optional<Account> founded = accountRepository.findByLogin("somelogin");
        assertFalse(founded.isPresent());
    }

    @Test
    public void testB_trueIfLoginExists() {
        Optional<Account> founded = accountRepository.findByLogin("login@foo.bar");
        assertTrue(founded.isPresent());
    }
}


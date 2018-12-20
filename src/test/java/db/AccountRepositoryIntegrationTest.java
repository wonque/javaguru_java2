package db;

import lv.java2.shopping_list.config.SpringAppConfig;
import lv.java2.shopping_list.db.AccountRepository;
import lv.java2.shopping_list.domain.Account;
import lv.java2.shopping_list.domain.ShoppingList;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static junit.framework.TestCase.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountRepositoryIntegrationTest {

    @Autowired
    private AccountRepository accountRepository;

    private Account account = new Account("onetwo@in.out", "password");


    @Test
    public void testAtrueIfAdded() {
        assertNull(account.getId());
        accountRepository.addToBase(account);
        assertNotNull(account.getId());
    }

    @Test
    public void testBtrueIfExistsByLogin() {
        assertTrue(accountRepository.checkIfLoginExists("onetwo@in.out"));
    }

    @Test
    public void testCfalseIfNotExistsByLogin() {
        assertFalse(accountRepository.checkIfLoginExists("somelogin"));
    }

    @Test
    public void testDtrueIfLoginAndPassCorrect() {
        Optional<Account> optionalAccount = accountRepository.findByLoginAndPass("onetwo@in.out", "password");
        assertTrue(optionalAccount.isPresent());
    }

    @Test
    public void testEtrueIfAllShoppingListFounded() {
        Optional<Account> optionalAccount = accountRepository.findByLoginAndPass("onetwo@in.out", "password");
        assertEquals(0, accountRepository.findAllActiveLists(optionalAccount.get()).size());
    }

    @Test
    public void testFtrueIfDeleted() {
        Optional<Account> optionalAccount = accountRepository.findByLoginAndPass("onetwo@in.out", "password");
        account = optionalAccount.get();
        assertTrue(accountRepository.deleteAccount(account));
    }

}

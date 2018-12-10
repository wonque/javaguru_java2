package db;

import lv.java2.shopping_list.config.SpringAppConfig;
import lv.java2.shopping_list.db.AccountRepository;
import lv.java2.shopping_list.domain.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static junit.framework.TestCase.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)
public class AccountRepositoryIntegrationTest {

    @Autowired
    private AccountRepository accountRepository;

    private Account account;

    @Before
    public void init() {
        this.account = new Account("onetwo@in.out", "password");
    }

    @Test
    public void returnAccountWithIdIfAddedToBase() {
        assertNull(account.getId());
        accountRepository.addToBase(account);
        assertNotNull(account.getId());
    }

    @Test
    public void returnTrueIfAccountIsInDataBase() {
        assertTrue(accountRepository.checkIfLoginExists("onetwo@in.out"));
    }

    @Test
    public void returnFalseIfAccountNotInDatabase() {
        assertFalse(accountRepository.checkIfLoginExists("somelogin"));
    }

    @Test
    public void returnTrueIfLoginAndPasswordCorrect() {
        Optional<Account> optionalAccount = accountRepository.findByLoginAndPass("onetwo@in.out", "password");
        assertTrue(optionalAccount.isPresent());
    }

    @Test
    public void returnTrueIfAccountDeleted() {
        assertTrue(accountRepository.deleteAccount(account));
    }

}

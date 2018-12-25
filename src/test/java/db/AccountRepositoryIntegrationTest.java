package db;


import lv.java2.shopping_list.config.SpringAppConfig;
import lv.java2.shopping_list.db.AccountRepository;
import lv.java2.shopping_list.domain.account.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static junit.framework.TestCase.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)
public class AccountRepositoryIntegrationTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testAtrueIfAdded() {
        Account account = new Account();
        account.setLogin("login@foo.bar");
        account.setPassword("123456");
        assertNull(account.getId());
        accountRepository.addToBase(account);
        assertNotNull(account.getId());
    }


//    @Test
//    public void testA_falseIfLoginDoesNotExists() {
//        Optional<Account> founded = accountRepository.findByLogin("somelogin");
//        assertFalse(founded.isPresent());
//    }
//
//    @Test
//    public void testB_trueIfLoginExists() {
//        Optional<Account> founded = accountRepository.findByLogin("onetwo@in.out");
//        assertTrue(founded.isPresent());
//    }


}

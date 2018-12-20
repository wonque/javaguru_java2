package db;

import lv.java2.shopping_list.config.SpringAppConfig;
import lv.java2.shopping_list.db.AccountRepository;
import lv.java2.shopping_list.db.ShoppingListItemRepository;
import lv.java2.shopping_list.db.ShoppingListRepository;
import lv.java2.shopping_list.domain.Account;
import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.domain.factories.AccountFactory;
import lv.java2.shopping_list.domain.factories.ShoppingListFactory;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ShoppingListRepositoryTest {


    @Autowired
    private ShoppingListRepository listRepository;

    @Autowired
    private ShoppingListItemRepository itemRepository;

    @Autowired
    private AccountRepository accountRepository;

    private static Account account;
    private static ShoppingList shoppingList;
    private static ShoppingListFactory shoppingListFactory;
    private static AccountFactory accountFactory;

    @BeforeClass
    public static void init() {
        accountFactory = new AccountFactory();
        account = accountFactory.buildInstance("login@login.com", "12345", "Dude");
        shoppingListFactory = new ShoppingListFactory();
        shoppingList = shoppingListFactory.createInstance(account, "list1");
    }

    @Test
    public void testAtrueIfAddedTodatabase() {
        accountRepository.addToBase(account);
        assertNull(shoppingList.getId());
        listRepository.addToDataBase(shoppingList);
        assertNotNull(shoppingList.getId());
    }

    @Test
    public void testBtrueIfFoundedByAccountAndTitle() {
        Optional<Account> optionalAccount =
                accountRepository.findByLoginAndPass(account.getLogin(), account.getPassword());

        Optional<ShoppingList> optionalShoppingList =
                listRepository.findByAccountAndTitle(optionalAccount.get(), "list1");

        assertTrue(optionalShoppingList.isPresent());
    }

    @Test
    public void testCtrueIfShoppingListDeleted() {
        Optional<Account> optionalAccount = accountRepository.
                findByLoginAndPass(account.getLogin(), account.getPassword());

        Optional<ShoppingList> optionalShoppingList = listRepository.findByAccountAndTitle(optionalAccount.get(),
                "list1");

        assertTrue(listRepository.remove(optionalShoppingList.get()));

        accountRepository.deleteAccount(optionalAccount.get());
    }
}



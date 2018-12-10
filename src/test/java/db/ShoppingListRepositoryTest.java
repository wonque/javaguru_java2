package db;

import lv.java2.shopping_list.config.SpringAppConfig;
import lv.java2.shopping_list.db.ShoppingListItemRepository;
import lv.java2.shopping_list.db.ShoppingListRepository;
import lv.java2.shopping_list.domain.Account;
import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.domain.builders.ShoppingListBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)
public class ShoppingListRepositoryTest {

    @Autowired
    private ShoppingListRepository listRepository;
    @Autowired
    private ShoppingListBuilder shoppingListBuilder;
    @Autowired
    private ShoppingListItemRepository itemRepository;

    private Account account;
    private ShoppingList shoppingList;


    @Before
    public void init() {
        this.account = new Account("ololo@gv.gv", "123456");
        account.setId(1L);
        this.shoppingList = shoppingListBuilder.createInstance(account, "list1");
    }

    @Test
    public void returnTrueIfShoppingListAddedToDatabase() {
        assertNull(shoppingList.getId());
        listRepository.addToDataBase(shoppingList);
        assertNotNull(shoppingList.getId());
    }

    @Test
    public void returnTrueIfShoppinglistFoundedInDataBase() {
        Optional<ShoppingList> optionalShoppingList = listRepository.findByAccountAndTitle(account, "list1");
        assertTrue(optionalShoppingList.isPresent());
    }

    //remove() method return foreign key constraint error - is tested in RemoveShoppingListService
}

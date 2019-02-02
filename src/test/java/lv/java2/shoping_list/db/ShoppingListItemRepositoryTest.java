//package shoping_list.db;
//
//import lv.java2.shopping_list.config.SpringAppConfig;
//import lv.java2.shopping_list.account.repository.AccountRepository;
//import lv.java2.shopping_list.item.repository.ItemRepository;
//import lv.java2.shopping_list.shoppinglist.repository.ShoppingListRepository;
//import lv.java2.shopping_list.account.domain.Account;
//import lv.java2.shopping_list.shoppinglist.domain.ShoppingList;
//import lv.java2.shopping_list.item.domain.Item;
//import lv.java2.shopping_list.account.domain.AccountFactory;
//import lv.java2.shopping_list.shoppinglist.domain.ShoppingListFactory;
//import lv.java2.shopping_list.item.domain.ItemFactory;
//import org.junit.BeforeClass;
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.math.BigDecimal;
//import java.util.Optional;
//
//import static junit.framework.TestCase.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = SpringAppConfig.class)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class ShoppingListItemRepositoryTest {
//
//    @Autowired
//    private ItemRepository itemRepository;
//    @Autowired
//    private AccountRepository accountRepository;
//    @Autowired
//    private ShoppingListRepository shoppingListRepository;
//
//    private static Account account;
//    private static ShoppingList shoppingList;
//    private static ShoppingListFactory shoppingListFactory;
//    private static AccountFactory accountFactory;
//    private static Item item;
//    private static ItemFactory itemBuilder;
//
//    @BeforeClass
//    public static void init() {
//        accountFactory = new AccountFactory();
//        account = accountFactory.buildInstance("login@login.com", "12345", "Dude");
//        shoppingListFactory = new ShoppingListFactory();
//        itemBuilder = new ItemFactory();
//    }
//
//
//    @Test
//    public void testingItemRepository() {
//        account = accountRepository.addToBase(account);
//        shoppingList = shoppingListFactory.createInstance(account, "list1");
//        shoppingList = shoppingListRepository.addToDataBase(shoppingList);
//        item = itemBuilder.createWithTitle(shoppingList, "item1");
//
//        assertNull(item.getItemId());
//        itemRepository.addItemToShoppingList(item);
//        assertNotNull(item.getItemId());
//
//        Optional<Item> shoppingListItem = itemRepository.findItemByTitle(shoppingList, "item1");
//        assertTrue(shoppingListItem.isPresent());
//
//        assertNull(shoppingListItem.get().getDescription());
//        String description = "any description";
//        assertEquals(1, itemRepository.updateDescription(shoppingListItem.get(), description));
//
//        assertNull(shoppingListItem.get().getPrice());
//        BigDecimal price = new BigDecimal(100);
//        assertEquals(1, itemRepository.updatePrice(shoppingListItem.get(), price));
//
//        assertTrue(itemRepository.removeSingleItem(shoppingListItem.get()));
//        shoppingListItem = itemRepository.findItemByTitle(shoppingList, "item1");
//        assertFalse(shoppingListItem.isPresent());
//
//        shoppingListRepository.remove(shoppingList);
//        Optional<ShoppingList> optionalShoppingList =
//                shoppingListRepository.findByAccountAndTitle(account, shoppingList.getTitle());
//        assertFalse(optionalShoppingList.isPresent());
//
//        accountRepository.deleteAccount(account);
//    }
//
//}

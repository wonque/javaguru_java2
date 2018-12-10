package db;

import lv.java2.shopping_list.config.SpringAppConfig;
import lv.java2.shopping_list.db.ShoppingListItemRepository;
import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.domain.ShoppingListItem;
import lv.java2.shopping_list.domain.builders.ShoppingListItemBuilder;
import lv.java2.shopping_list.services.remove.item.ItemRemoveRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)
public class ShoppingListItemRepositoryTest {

    @Autowired
    private ShoppingListItemRepository itemRepository;
    @Autowired
    private ShoppingListItemBuilder itemBuilder;

    private ShoppingList shoppingList;
    private ShoppingListItem item;

    @Before
    public void init() {
        shoppingList = new ShoppingList();
        shoppingList.setId(1L);
        shoppingList.setTitle("list1");
        this.item = itemBuilder.createWithTitle(shoppingList, "ItemTitle");
    }

    @Test
    public void returnTrueIfItemAddedToBase() {
        assertNull(item.getItemId());
        itemRepository.addItemToShoppingList(item);
        assertNotNull(item.getItemId());
    }

    @Test
    public void returnTrueIfItemIfInDataBase() {
        Optional<ShoppingListItem> shoppingListItem = itemRepository.findItemByTitle(shoppingList, "ItemTitle");
        assertTrue(shoppingListItem.isPresent());
    }

    @Test
    public void returnTrueIfItemDescriptionUpdated() {
        String description = "any description";
        Optional<ShoppingListItem> shoppingListItem = itemRepository.findItemByTitle(shoppingList, "ItemTitle");
        if (shoppingListItem.isPresent()) {
            item = shoppingListItem.get();
            assertTrue(itemRepository.updateDescription(item, description));

        }
    }

    @Test
    public void returnTrueIfAllItemsInShoppingListDeleted(){
        ShoppingListItem item = new ShoppingListItem();
        item.setShoppingList(shoppingList);
        item.setTitle("ITEM6");
        itemRepository.removeSingleItem(item);
    }

}

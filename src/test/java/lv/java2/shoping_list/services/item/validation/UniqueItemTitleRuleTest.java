package lv.java2.shoping_list.services.item.validation;

import lv.java2.shopping_list.domain.Item;
import lv.java2.shopping_list.repository.ItemRepository;
import lv.java2.shopping_list.services.item.validation.UniqueItemTitleRule;
import lv.java2.shopping_list.web.dto.ItemDTO;
import lv.java2.shopping_list.web.exceptions.DuplicateResourceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UniqueItemTitleRuleTest {

    @Mock
    private ItemRepository itemRepository;
    @InjectMocks
    @Spy
    private UniqueItemTitleRule uniqueItemTitleRule;

    private ItemDTO itemDTO;
    private Item item;

    @Before
    public void setup() {
        itemDTO = itemDTO();
        item = new Item();
    }

    @Test
    public void shouldThrowException() {
        when(itemRepository.findByTitle("Milk")).thenReturn(Optional.of(item));

        assertThatThrownBy(() -> uniqueItemTitleRule.validate(itemDTO))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessage("Item with title = 'Milk' already exists!");
    }

    @Test
    public void shouldValidate() {
        when(itemRepository.findByTitle("Milk")).thenReturn(Optional.empty());

        uniqueItemTitleRule.validate(itemDTO);

        verify(uniqueItemTitleRule).validate(itemDTO);
    }


    private ItemDTO itemDTO() {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setTitle("Milk");
        itemDTO.setDescription("Description");
        itemDTO.setPrice(100.0);
        return itemDTO;
    }

}

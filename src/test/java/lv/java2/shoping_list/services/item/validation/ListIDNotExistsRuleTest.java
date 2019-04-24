package lv.java2.shoping_list.services.item.validation;

import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.services.item.validation.ListIDNotExistsRule;
import lv.java2.shopping_list.web.dto.ItemDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ListIDNotExistsRuleTest {

    @Mock
    private ShoppingListRepository listRepository;

    @InjectMocks
    @Spy
    private ListIDNotExistsRule rule;

    private ItemDTO itemDTO;

    @Before
    public void setup() {
        itemDTO = new ItemDTO();
        itemDTO.setListId(1L);
    }

    @Test
    public void shouldThrowException() {
        itemDTO.setListId(1L);
        when(listRepository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> rule.validate(itemDTO))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Shopping list not found");
    }

    @Test
    public void shouldValidate() {
        itemDTO.setListId(1L);
        when(listRepository.existsById(1L)).thenReturn(true);

        rule.validate(itemDTO);

        verify(rule).validate(itemDTO);
    }
}

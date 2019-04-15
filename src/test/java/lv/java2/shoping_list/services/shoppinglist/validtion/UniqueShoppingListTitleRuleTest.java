package lv.java2.shoping_list.services.shoppinglist.validtion;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.services.shoppinglist.validation.UniqueShoppingListTitleRule;
import lv.java2.shopping_list.web.dto.ShoppingListDTO;
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
public class UniqueShoppingListTitleRuleTest {

    @Mock
    private ShoppingListRepository repository;
    private ShoppingListDTO dto;
    private ShoppingList shoppingList;

    @InjectMocks
    @Spy
    public UniqueShoppingListTitleRule rule;

    @Before
    public void setup() {
        this.dto = new ShoppingListDTO();
        this.shoppingList = new ShoppingList();
        dto.setTitle("Title");
        dto.setUserId(100L);
    }

    @Test
    public void shouldThrowException() {
        when(repository.findByUserIdAndTitle(dto.getUserId(), dto.getTitle()))
                .thenReturn(Optional.of(shoppingList));

        assertThatThrownBy(() -> rule.validate(dto))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessage("Shopping list with title = 'Title' already exists!");

        verify(rule).validate(dto);
    }

    @Test
    public void shouldValidateDTO() {
        when(repository.findByUserIdAndTitle(dto.getUserId(), dto.getTitle()))
                .thenReturn(Optional.empty());

        rule.validate(dto);

        verify(rule).validate(dto);
    }
}

package lv.java2.shoping_list.services.shoppinglist.validtion;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.services.shoppinglist.validation.ListNotExistsRule;
import lv.java2.shopping_list.web.dto.ShoppingListDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ListNotExistsRuleTest {

    @Mock
    private ShoppingListRepository repository;
    private ShoppingListDTO dto;
    private ShoppingList shoppingList;

    @InjectMocks
    @Spy
    public ListNotExistsRule rule;

    @Before
    public void setup() {
        dto = listDto();
        shoppingList = new ShoppingList();
    }

    @Test
    public void shouldThrowException() {
        when(repository.findByUserIdAndListId(dto.getUserId(), dto.getId())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> rule.validate(dto))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Shopping list not found!");
    }

    @Test
    public void shouldValidateDTO() {
        when(repository.findByUserIdAndListId(dto.getUserId(), dto.getId()))
                .thenReturn(Optional.of(shoppingList));

        rule.validate(dto);

        verify(rule).validate(dto);
    }

    private ShoppingListDTO listDto() {
        ShoppingListDTO dto = new ShoppingListDTO();
        dto.setUserId(100L);
        dto.setId(200L);
        return dto;
    }

}

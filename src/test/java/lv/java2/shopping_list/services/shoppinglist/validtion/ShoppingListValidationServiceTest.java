package lv.java2.shopping_list.services.shoppinglist.validtion;

import lv.java2.shopping_list.dto.ShoppingListDTO;
import lv.java2.shopping_list.services.shoppinglist.validation.ShoppingListValidationRule;
import lv.java2.shopping_list.services.shoppinglist.validation.ShoppingListValidationService;
import lv.java2.shopping_list.services.shoppinglist.validation.UniqueShoppingListTitleRule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingListValidationServiceTest {

    @Mock
    private UniqueShoppingListTitleRule uniqueShoppingListTitleRule;

    private ShoppingListDTO dto;
    private ShoppingListValidationService validationService;
    @Captor
    private ArgumentCaptor<ShoppingListDTO> dtoCaptor;

    @Before
    public void setup() {
        Set<ShoppingListValidationRule> rules = new HashSet<>();
        rules.add(uniqueShoppingListTitleRule);
        dto = listDto();
        validationService = new ShoppingListValidationService(rules);
    }

    @Test
    public void shouldValidate() {
        validationService.validate(dto);

        verify(uniqueShoppingListTitleRule).validate(dtoCaptor.capture());

        List<ShoppingListDTO> result = dtoCaptor.getAllValues();

        assertThat(result.size()).isEqualTo(1);
        assertThat(result).containsOnly(dto);

    }

    private ShoppingListDTO listDto() {
        ShoppingListDTO dto = new ShoppingListDTO();
        dto.setUserId(100L);
        dto.setId(200L);
        return dto;
    }


}

package lv.java2.shoping_list.services.shoppinglist.validtion;

import lv.java2.shopping_list.services.shoppinglist.validation.ListNotExistsRule;
import lv.java2.shopping_list.services.shoppinglist.validation.ShoppingListValidationRule;
import lv.java2.shopping_list.services.shoppinglist.validation.ShoppingListValidationService;
import lv.java2.shopping_list.services.shoppinglist.validation.UniqueTitleRule;
import lv.java2.shopping_list.web.dto.ShoppingListDTO;
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
    private ListNotExistsRule notExistsRule;
    @Mock
    private UniqueTitleRule uniqueTitleRule;

    private ShoppingListDTO dto;
    private ShoppingListValidationService validationService;
    @Captor
    private ArgumentCaptor<ShoppingListDTO> dtoCaptor;

    @Before
    public void setup() {
        Set<ShoppingListValidationRule> rules = new HashSet<>();
        rules.add(notExistsRule);
        rules.add(uniqueTitleRule);
        dto = listDto();
        validationService = new ShoppingListValidationService(rules);
    }

    @Test
    public void shouldValidate() {
        validationService.validate(dto);

        verify(notExistsRule).validate(dtoCaptor.capture());
        verify(uniqueTitleRule).validate(dtoCaptor.capture());

        List<ShoppingListDTO> result = dtoCaptor.getAllValues();

        assertThat(result.size()).isEqualTo(2);
        assertThat(result).containsOnly(dto);

    }

    private ShoppingListDTO listDto() {
        ShoppingListDTO dto = new ShoppingListDTO();
        dto.setUserId(100L);
        dto.setId(200L);
        return dto;
    }


}

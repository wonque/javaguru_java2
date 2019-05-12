package lv.java2.shopping_list.services.shoppinglist.update;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.dto.ShoppingListDTO;
import lv.java2.shopping_list.dto.mappers.ShoppingListMapper;
import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.services.shoppinglist.validation.ShoppingListValidationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingListUpdateEntityServiceTests {

    @Mock
    private ShoppingListRepository repository;
    @Mock
    private ShoppingListValidationService validator;
    @Mock
    private ShoppingListMapper mapper;

    @Captor
    public ArgumentCaptor<ShoppingListDTO> listDtoCaptor;

    @InjectMocks
    private ShoppingListUpdateService updateService;

    private ShoppingListDTO requestDto = new ShoppingListDTO();
    private ShoppingList shoppingList = new ShoppingList();

    @Before
    public void init() {
        requestDto.setId(3001L);
        requestDto.setTitle("title");
        requestDto.setUserId(1L);
        requestDto.setCategory("category");
        shoppingList.setId(3001L);
        shoppingList.setTitle("OtherTitle");
        shoppingList.setCategory("OtherCategory");

    }

    @Test
    public void shouldReturnUpdatedDto() {
        when(repository.getOne(requestDto.getId())).thenReturn(shoppingList);
        when(repository.save(shoppingList)).thenReturn(shoppingList);
        when(mapper.toDTO(shoppingList)).thenReturn(requestDto);

        ShoppingListDTO responseDto = updateService.update(requestDto);
        verify(validator).validate(listDtoCaptor.capture());
        ShoppingListDTO captorResult = listDtoCaptor.getValue();

        assertThat(captorResult).isEqualTo(requestDto);
        assertThat(responseDto).isEqualTo(requestDto);
    }


}

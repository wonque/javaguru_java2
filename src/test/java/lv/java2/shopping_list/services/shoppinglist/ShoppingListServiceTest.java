package lv.java2.shopping_list.services.shoppinglist;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.domain.ShoppingListStatus;
import lv.java2.shopping_list.domain.User;
import lv.java2.shopping_list.dto.ShoppingListDTO;
import lv.java2.shopping_list.dto.mappers.ShoppingListMapper;
import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.services.shoppinglist.validation.ShoppingListValidationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingListServiceTest {

    @Mock
    private ShoppingListRepository repository;
    @Mock
    private ShoppingListMapper mapper;
    @Mock
    private ShoppingListValidationService validator;

    @Captor
    ArgumentCaptor<ShoppingListDTO> listDTOArgumentCaptor;

    private User user;
    private ShoppingList shoppingList;
    private ShoppingListDTO shoppingListDTO;
    private ShoppingListService testObj;

    @Before
    public void setup() {
        testObj = new ShoppingListService(repository, mapper, validator);
        user = user();
        shoppingList = shoppingList();
        shoppingListDTO = shoppingListDTO();
    }

    @Test
    public void shouldCreate() {

        when(mapper.toDomain(shoppingListDTO)).thenReturn(shoppingList);
        when(repository.save(shoppingList)).thenReturn(shoppingList);
        when(mapper.toDTO(shoppingList)).thenReturn(shoppingListDTO);

        ShoppingListDTO result = testObj.create(shoppingListDTO);

        assertThat(result.getId()).isEqualTo(12L);
    }

    @Test
    public void shouldFindAllById() {
        List<ShoppingList> returnedList = new ArrayList<>();
        returnedList.add(shoppingList);

        when(repository.findAllByUserId(user.getId())).thenReturn(returnedList);
        when(mapper.toDTO(shoppingList)).thenReturn(shoppingListDTO);

        List<ShoppingListDTO> result = testObj.getAllByUserId(1001L);

        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void shouldFindSingleListBYUserIdAndListId() {

        when(repository.findByUserIdAndListId(user.getId(), shoppingList.getId()))
                .thenReturn(Optional.ofNullable(shoppingList));
        when(mapper.toDTO(shoppingList)).thenReturn(shoppingListDTO);

        ShoppingListDTO result = testObj.getSingleList(user.getId(), shoppingListDTO.getId());

        assertThat(result.getId()).isEqualTo(12L);
        assertThat(result).isEqualTo(shoppingListDTO);

    }

    @Test
    public void shouldCaptorDTOObjectInValidator() {

        when(mapper.toDomain(shoppingListDTO)).thenReturn(shoppingList);
        when(repository.save(shoppingList)).thenReturn(shoppingList);
        when(mapper.toDTO(shoppingList)).thenReturn(shoppingListDTO);

        testObj.create(shoppingListDTO);
        verify(validator).validate(listDTOArgumentCaptor.capture());
        ShoppingListDTO captorResult = listDTOArgumentCaptor.getValue();

        assertThat(captorResult).isEqualTo(shoppingListDTO);

    }

    @Test
    public void shouldUpdate() {
        shoppingListDTO.setCategory("UPDATED");
        shoppingList.setCategory("UPDATED");

        when(mapper.toDomain(shoppingListDTO)).thenReturn(shoppingList);
        when(repository.save(shoppingList)).thenReturn(shoppingList);
        when(mapper.toDTO(shoppingList)).thenReturn(shoppingListDTO);

        ShoppingListDTO result = testObj.create(shoppingListDTO);

        assertThat(result.getCategory()).isEqualTo("UPDATED");
    }

    private ShoppingListDTO shoppingListDTO() {
        ShoppingListDTO shoppingListDTO = new ShoppingListDTO();
        shoppingListDTO.setId(12L);
        shoppingListDTO.setUserId(1001L);
        shoppingListDTO.setTitle("LIST TITLE");
        shoppingListDTO.setCategory("TEST");
        shoppingListDTO.setStatus(ShoppingListStatus.ACTIVE);
        return shoppingListDTO;
    }

    private ShoppingList shoppingList() {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setId(12L);
        shoppingList.setTitle("LIST TITLE");
        shoppingList.setCategory("TEST");
        shoppingList.setStatus(ShoppingListStatus.ACTIVE);
        shoppingList.setUser(user());
        return shoppingList;
    }

    private User user() {
        User user = new User();
        user.setEmail("TEST@EMAIL.COM");
        user.setPassword("TESTPASSWORD123");
        user.setId(1001L);
        return user;
    }
}

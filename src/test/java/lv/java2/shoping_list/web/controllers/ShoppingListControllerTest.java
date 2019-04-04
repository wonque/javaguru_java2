package lv.java2.shoping_list.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lv.java2.shopping_list.ShoppingListApp;
import lv.java2.shopping_list.services.shoppinglist.ShoppingListDBValidator;
import lv.java2.shopping_list.services.shoppinglist.addition.ShoppingListAdditionService;
import lv.java2.shopping_list.services.shoppinglist.get.GetShoppingListService;
import lv.java2.shopping_list.services.shoppinglist.removal.ShoppingListRemovalService;
import lv.java2.shopping_list.services.shoppinglist.update.ShoppingListUpdateService;
import lv.java2.shopping_list.web.controllers.ShoppingListController;
import lv.java2.shopping_list.web.dto.ShoppingListDTO;
import lv.java2.shopping_list.web.exceptions.ResourceNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ShoppingListController.class, secure = false)
@ContextConfiguration(classes = {ShoppingListApp.class})
public class ShoppingListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetShoppingListService getListService;
    @MockBean
    private ShoppingListAdditionService additionService;
    @MockBean
    private ShoppingListRemovalService removalService;
    @MockBean
    private ShoppingListUpdateService updateService;
    @MockBean
    private ShoppingListDBValidator shoppingListDBValidator;
    @MockBean
    private Validator validator;

    private List<ShoppingListDTO> shoppingLists = new ArrayList<>();
    private Set<ConstraintViolation<ShoppingListDTO>> violations = new HashSet<>();
    private ObjectMapper mapper = new ObjectMapper();
    private ShoppingListDTO shoppingListDTO = new ShoppingListDTO(1L, "title");

    @Test
    public void createList_return201CreatedStatusAndListDTO() throws Exception {
        shoppingListDTO.setId(1L);
        String toPost = mapper.writeValueAsString(shoppingListDTO);
        Mockito.when(validator.validate(shoppingListDTO)).thenReturn(violations);
        Mockito.when(additionService.addList(shoppingListDTO)).thenReturn(shoppingListDTO);
        mockMvc.perform(post("/users/1/lists")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(toPost))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/users/1/lists/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void updateList_return200OKStatusAndDTO() throws Exception {
        shoppingListDTO.setId(1L);
        String toPost = mapper.writeValueAsString(shoppingListDTO);
        Mockito.when(validator.validate(shoppingListDTO)).thenReturn(violations);
        Mockito.when(additionService.addList(shoppingListDTO)).thenReturn(shoppingListDTO);
        mockMvc.perform(put("/users/1/lists/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(toPost))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getAllByUserID_return200OKStatusAndListOfDTOs() throws Exception {
        Mockito.when(getListService.getAllByUserId(1L)).thenReturn(shoppingLists);
        Mockito.when(shoppingListDBValidator.isUserExists(1L)).thenReturn(true);
        mockMvc.perform(get("/users/1/lists")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void getAllByUserId_404NotFoundWhenExceptionIsThrown() throws Exception {
        Mockito.when(getListService.getAllByUserId(1L)).thenThrow(new ResourceNotFoundException("Not found"));
        mockMvc.perform(get("/users/1/lists")).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getSingleById_return200IsOkAndShoppingListDTO() throws Exception {
        Mockito.when(getListService.getSingleById(1L, 1L)).thenReturn(shoppingListDTO);
        Mockito.when(shoppingListDBValidator.isUserExists(1L)).thenReturn(true);
        mockMvc.perform(get("/users/1/lists/1")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{title: title}"));
    }

    @Test
    public void getSingleById_return404NotFoundWhenExceptionIsThrown() throws Exception {
        Mockito.when(getListService.getSingleById(1L, 1L))
                .thenThrow(new ResourceNotFoundException("Not Found"));
        mockMvc.perform(get("/users/1/lists/1")).andExpect(status().isNotFound());
    }

}

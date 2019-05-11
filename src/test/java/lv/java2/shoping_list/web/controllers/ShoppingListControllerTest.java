package lv.java2.shoping_list.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lv.java2.shopping_list.ShoppingListApp;
import lv.java2.shopping_list.controllers.ShoppingListController;
import lv.java2.shopping_list.dto.ShoppingListDTO;
import lv.java2.shopping_list.services.shoppinglist.addition.ShoppingListAdditionService;
import lv.java2.shopping_list.services.shoppinglist.get.GetShoppingListService;
import lv.java2.shopping_list.services.shoppinglist.removal.ShoppingListRemovalService;
import lv.java2.shopping_list.services.shoppinglist.update.ShoppingListUpdateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;
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
    private Validator validator;

    private List<ShoppingListDTO> shoppingLists = new ArrayList<>();
    private Set<ConstraintViolation<ShoppingListDTO>> violations = new HashSet<>();
    private ObjectMapper mapper = new ObjectMapper();
    private ShoppingListDTO shoppingListDTO = new ShoppingListDTO(1L, "title");

    @Test
    public void createList_return201CreatedStatusAndListDTO() throws Exception {

        shoppingListDTO.setId(1L);
        String toPost = mapper.writeValueAsString(shoppingListDTO);

        when(validator.validate(shoppingListDTO)).thenReturn(violations);
        when(additionService.addList(shoppingListDTO)).thenReturn(shoppingListDTO);

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

        when(validator.validate(shoppingListDTO)).thenReturn(violations);
        when(additionService.addList(shoppingListDTO)).thenReturn(shoppingListDTO);

        mockMvc.perform(put("/users/1/lists/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(toPost))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getAllByUserID_return200OKStatusAndListOfDTOs() throws Exception {

        when(getListService.getAllByUserId(1L)).thenReturn(shoppingLists);

        mockMvc.perform(get("/users/1/lists")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void getAllByUserId_404NotFoundWhenExceptionIsThrown() throws Exception {

        when(getListService.getAllByUserId(1L)).thenThrow(new EntityNotFoundException("Not found"));

        mockMvc.perform(get("/users/1/lists")).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getSingleById_return200IsOkAndShoppingListDTO() throws Exception {

        when(getListService.getSingleById(1L, 1L)).thenReturn(shoppingListDTO);

        mockMvc.perform(get("/users/1/lists/1")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{title: title}"));
    }

    @Test
    public void getSingleById_return404NotFoundWhenExceptionIsThrown() throws Exception {

        when(getListService.getSingleById(1L, 1L))
                .thenThrow(new EntityNotFoundException("Not Found"));

        mockMvc.perform(get("/users/1/lists/1")).andExpect(status().isNotFound());
    }

    @Test
    public void delete_returnNoContentStatus() throws Exception {

        mockMvc.perform(delete("/users/1/lists/1"))
                .andExpect(status().isNoContent());
    }

}

package lv.java2.shoping_list.user.web;

import lv.java2.shopping_list.ShoppingListApp;
import lv.java2.shopping_list.services.user.get.GetUserService;
import lv.java2.shopping_list.services.user.registration.UserRegistrationService;
import lv.java2.shopping_list.web.controllers.UserController;
import lv.java2.shopping_list.web.dto.UserDTO;
import lv.java2.shopping_list.web.dto.mappers.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
@ContextConfiguration(classes = {ShoppingListApp.class})
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private GetUserService getUserService;

    @MockBean
    private UserRegistrationService userRegistrationService;

    @Test
    public void getAccount_returnResponseWithError() throws Exception {
        UserDTO userDTO = new UserDTO();
        this.mockMvc.perform(get("/users/1")).andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8));
    }

    @Test
    public void getAccount_returnResponseWithAccount() throws Exception {
        UserDTO userDTO = new UserDTO();
        Mockito.when(getUserService.findById(1L))
                .thenReturn(userDTO);
        this.mockMvc.perform(get("/users/1")).andDo(print())
                .andExpect(status().isOk()).andExpect(content().json("{}"));
    }
}

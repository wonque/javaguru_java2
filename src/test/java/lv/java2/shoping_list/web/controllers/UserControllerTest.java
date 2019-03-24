package lv.java2.shoping_list.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lv.java2.shopping_list.ShoppingListApp;
import lv.java2.shopping_list.services.user.get.GetUserService;
import lv.java2.shopping_list.services.user.registration.UserRegistrationService;
import lv.java2.shopping_list.web.controllers.UserController;
import lv.java2.shopping_list.web.dto.UserDTO;
import lv.java2.shopping_list.web.dto.mappers.UserMapper;
import lv.java2.shopping_list.web.exceptions.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    private UserDTO requestDTO = new UserDTO();

    @Before
    public void initUserDTO(){
        requestDTO.setEmail("email@valid.one");
        requestDTO.setPassword("passwordOne1Two2Three3");
        requestDTO.setUsername("Yevlampiy");
    }

//    @Test
//    public void returnsDTOWhenUserSuccessfullyRegistered() throws Exception {
//        UserDTO userDTO = new UserDTO();
//        userDTO.setUserId(1L);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonContent = mapper.writeValueAsString(requestDTO);
//        Mockito.when(userRegistrationService.register(requestDTO)).thenReturn(userDTO);
//        this.mockMvc.perform(post("/register")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(jsonContent)).andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(content().json("{ userId: 1 }"));
//
//    }

    @Test
    public void throwsExceptionWhenUserNotFoundById() throws Exception {
        Mockito.when(getUserService.findById(100L)).thenThrow(new ResourceNotFoundException("User with ID = 100 not found!"));
        this.mockMvc.perform(get("/users/100")).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void returnOKStatusWithDTOAndEmptyJsonContent() throws Exception {
        UserDTO userDTO = new UserDTO();
        Mockito.when(getUserService.findById(1L))
                .thenReturn(userDTO);
        this.mockMvc.perform(get("/users/1")).andDo(print())
                .andExpect(matchAll(status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON_UTF8),
                        content().json("{}")));
    }
}

package lv.java2.shoping_list.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;

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
    private UserRegistrationService registrationService;

    @MockBean
    private GetUserService getUserService;

    @MockBean
    private Validator validator;


    private UserDTO requestDTO = new UserDTO();
    private Set<ConstraintViolation<UserDTO>> violations = new HashSet<>();
    private ObjectMapper mapper = new ObjectMapper();


    @Test
    public void returnsCreatedStatusAndLocationHeaderWhenUserRegistered() throws Exception {
        UserDTO responseDTO = new UserDTO();
        responseDTO.setUserId(1L);
        String jsonContent = mapper.writeValueAsString(requestDTO);
        Mockito.when(validator.validate(requestDTO)).thenReturn(violations);
        Mockito.when(registrationService.register(requestDTO)).thenReturn(responseDTO);
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(jsonContent)).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void throwsExceptionWhenUserNotFoundById() throws Exception {
        Mockito.when(getUserService.findById(100L))
                .thenThrow(new EntityNotFoundException("User with ID = 100 not found!"));
        mockMvc.perform(get("/users/100")).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void returnOKStatusWithDTOAndEmptyJsonContent() throws Exception {
        UserDTO userDTO = new UserDTO();
        Mockito.when(getUserService.findById(1L))
                .thenReturn(userDTO);
        mockMvc.perform(get("/users/1")).andDo(print())
                .andExpect(matchAll(status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON_UTF8),
                        content().json("{}")));
    }
}

package lv.java2.shopping_list.controllers;

import lv.java2.shopping_list.dto.UserDTO;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(secure = false)
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void shouldRegisterUser() throws Exception {

        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObject()))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", Matchers.endsWith("/users/" + userDTO().getUserId())));
    }


    @Test
    public void shouldFindUserByID() throws Exception {

        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObject()))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", Matchers.endsWith("/users/" + userDTO().getUserId())));

        mockMvc.perform(get("/users/1")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1L));
    }

    private UserDTO userDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("TEST@EMAIL.COM");
        userDTO.setPassword("TESTPASSWORD123");
        userDTO.setUserId(1L);
        return userDTO;
    }

    private String jsonObject() throws JSONException {
        return new JSONObject().put("email", "TEST@EMAIL.COM")
                .put("password", "TESTPASSWORD123")
                .toString();
    }


}

package lv.java2.shopping_list.controllers;

import org.hamcrest.Matchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(secure = false)
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ShoppingListControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void postUser() throws Exception {

        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJsonObject()))
                .andExpect(status().isCreated());
    }


    @Test
    public void shouldCreate() throws Exception {

        performPostList();

    }

    @Test
    public void shouldFindByUserIdAndListId() throws Exception {

        performPostList();

        mockMvc.perform(get("/users/1/lists/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("TEST_TITLE"))
                .andExpect(jsonPath("$.category").value("TEST_CATEGORY"))
                .andExpect(jsonPath("$.status").value("ACTIVE"));

    }

    @Test
    public void shouldGetAllByUserId() throws Exception {

        performPostList();

        mockMvc.perform(get("/users/1/lists/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].title").value("TEST_TITLE"))
                .andExpect(jsonPath("$[0].category").value("TEST_CATEGORY"));

    }

    @Test
    public void shouldUpdate() throws Exception {

        performPostList();

        mockMvc.perform(put("/users/1/lists/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedShoppingListJsonObject()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("UPDATED_TITLE"))
                .andExpect(jsonPath("$.category").value("UPDATED_CATEGORY"));
    }

    @Test
    public void shouldDelete() throws Exception {

    }

    private String shoppingListJsonObject() throws JSONException {
        return new JSONObject().put("title", "TEST_TITLE")
                .put("category", "TEST_CATEGORY")
                .toString();
    }

    private String updatedShoppingListJsonObject() throws JSONException {
        return new JSONObject().put("id", "1")
                .put("title", "UPDATED_TITLE")
                .put("category", "UPDATED_CATEGORY")
                .toString();
    }

    public String userJsonObject() throws JSONException {
        return new JSONObject().put("email", "TEST@EMAIL.COM")
                .put("password", "TESTPASSWORD123")
                .toString();
    }

    private void performPostList() throws Exception {

        mockMvc.perform(post("/users/1/lists")
                .contentType(MediaType.APPLICATION_JSON)
                .content(shoppingListJsonObject()))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", Matchers.endsWith("/lists/1")));

    }

}

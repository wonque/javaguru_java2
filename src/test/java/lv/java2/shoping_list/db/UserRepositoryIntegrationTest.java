package lv.java2.shoping_list.db;


import lv.java2.shopping_list.ShoppingListApp;
import lv.java2.shopping_list.domain.User;
import lv.java2.shopping_list.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Optional;

import static junit.framework.TestCase.*;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest (classes = ShoppingListApp.class)
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAtrueIfAdded() {
        User user = new User();
        user.setEmail("poooooo@foo.bar");
        user.setPassword("123456");
        assertNull(user.getId());
        userRepository.save(user);
        assertNotNull(user.getId());
    }


    @Test
    public void testA_falseIfLoginDoesNotExists() {
        Optional<User> founded = userRepository.findByEmail("somelogin");
        assertFalse(founded.isPresent());
    }

    @Test
    public void testB_trueIfLoginExists() {
        Optional<User> founded = userRepository.findByEmail("login@foo.bar");
        assertTrue(founded.isPresent());
    }
}


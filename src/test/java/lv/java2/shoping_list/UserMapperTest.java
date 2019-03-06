package lv.java2.shoping_list;

import lv.java2.shopping_list.user.domain.User;
import lv.java2.shopping_list.web.dto.UserDTO;
import lv.java2.shopping_list.web.dto.mappers.UserMapper;
import lv.java2.shopping_list.web.dto.mappers.UserMapperImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserMapperImpl.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;
    private UserDTO userDTO = new UserDTO("email@email.email", "password", "Vasya");

    @Test
    public void test(){
        User user = userMapper.toDomain(userDTO);
        assertNotNull(user.getEmail());
        assertEquals("email@email.email", user.getEmail());
        assertNotNull(user.getUsername());
        assertEquals("Vasya", user.getUsername());
        assertNull(user.getPassword());
        assertNull(user.getDateCreated());
    }


}

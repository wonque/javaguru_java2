package lv.java2.shoping_list.web;

import lv.java2.shopping_list.domain.User;
import lv.java2.shopping_list.web.dto.UserDTO;
import lv.java2.shopping_list.web.dto.mappers.UserMapper;
import lv.java2.shopping_list.web.dto.mappers.UserMapperImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserMapperImpl.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;
    private UserDTO userDTO = new UserDTO("email@email.email", "password", "Vasya");
    private User newUser = new User("mail@ololo.com", "poodd123", "Elma");

    @Test
    public void returnValidUserInstanceWithMappedProperties(){
        User user = userMapper.toDomain(userDTO);
        assertNotNull(user.getEmail());
        assertEquals("email@email.email", user.getEmail());
        assertNotNull(user.getUsername());
        assertEquals("Vasya", user.getUsername());
        assertNull(user.getPassword());
        assertNull(user.getDateCreated());
    }

    @Test
    public void returnDTOInstanceWithMappeduserProperties(){
        UserDTO resultDTO = userMapper.toDTO(newUser);
        assertNotNull(resultDTO.getEmail());
        assertNull(resultDTO.getPassword());
        assertNotNull(resultDTO.getUsername());
        assertEquals("mail@ololo.com", resultDTO.getEmail());
        assertEquals("Elma", resultDTO.getUsername());
    }


}
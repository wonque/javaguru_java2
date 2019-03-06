import lv.java2.shopping_list.ShoppingListApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ShoppingListApp.class})
public class ShoppingListAppContextLoadTest {


    @Test
    public void contextLoad() throws Exception {
    }
}

import org.junit.Test;
import lv.java2.shopping_list.services.UserInputValidation;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class UserInputValidationTest {

    UserInputValidation inputValidation = new UserInputValidation();


    @Test
    public void negativePriceTest() {
        assertFalse(inputValidation.isUserEnteredPriceBiggerThanZero(-9));
    }

    @Test
    public void positivePriceTest() {
        assertTrue(inputValidation.isUserEnteredPriceBiggerThanZero(9));
    }

    @Test
    public void falseIfProductTitleIsNotEmpty() {
        assertFalse(inputValidation.isEnteredProductTitleEmpty("title"));
    }

    @Test
    public void trueIfProductTitleIsEmpty() {
        assertTrue(inputValidation.isEnteredProductTitleEmpty(""));
    }


}

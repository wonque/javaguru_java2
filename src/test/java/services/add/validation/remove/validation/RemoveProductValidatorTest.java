package services.add.validation.remove.validation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import lv.java2.shopping_list.services.remove.RemoveProductRules;
import lv.java2.shopping_list.services.remove.RemoveProductValidator;

@RunWith(MockitoJUnitRunner.class)
public class RemoveProductValidatorTest {

    @Mock
    private RemoveProductRules rules;

    @InjectMocks
    private RemoveProductValidator validator;

}

package services.add;

import lv.java2.shopping_list.domain.Product;
import lv.java2.shopping_list.factories.ProductFactory;
import lv.java2.shopping_list.services.Error;
import lv.java2.shopping_list.services.add.product.ProductAdditionRequest;
import lv.java2.shopping_list.services.add.product.ProductAdditionResponse;
import lv.java2.shopping_list.services.add.product.ProductAdditionService;
import lv.java2.shopping_list.services.add.product.validation.ProductAdditionValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class ProductAdditionValidatorServiceTest {

    private ProductAdditionRequest request = new ProductAdditionRequest("milk");

    @Mock
    private ProductAdditionValidator validator;

    @Mock
    private ProductFactory factory;

    @Mock
    private List<Error> errors;

    @InjectMocks
    private ProductAdditionService productAdditionService = new ProductAdditionService();


    @Test
    public void shouldReturnResponseWithError() {
        Mockito.when(validator.validate(request)).thenReturn(errors);
        Mockito.when(errors.size()).thenReturn(1);
        ProductAdditionResponse response = productAdditionService.add(request);
        assertFalse(response.isSuccess());
        assertEquals(1, response.getErrors().size());
        Mockito.verifyZeroInteractions(factory);
    }

    @Test
    public void shouldReturnNoErrorsAndProductID() {
        Product product = Mockito.mock(Product.class);
        Mockito.when(validator.validate(request)).thenReturn(errors);
        Mockito.when(errors.isEmpty()).thenReturn(true);
        Mockito.when(factory.saveProductToBase(request.getTitle())).thenReturn(product);
        Mockito.when(product.getId()).thenReturn(4L);
        ProductAdditionResponse response = productAdditionService.add(request);
        assertEquals(4L, (long) response.getAddedProduct().getId());
        assertTrue(response.isSuccess());
    }
}

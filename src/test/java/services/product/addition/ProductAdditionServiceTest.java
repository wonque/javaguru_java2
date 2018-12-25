package services.product.addition;

import lv.java2.shopping_list.db.ProductRepository;
import lv.java2.shopping_list.domain.Product;
import lv.java2.shopping_list.domain.ProductBuilder;
import lv.java2.shopping_list.services.ShoppingListError;
import lv.java2.shopping_list.services.product.adition.ProductAdditionRequest;
import lv.java2.shopping_list.services.product.adition.ProductAdditionResponse;
import lv.java2.shopping_list.services.product.adition.ProductAdditionService;
import lv.java2.shopping_list.services.product.adition.validation.ProductAdditionValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class ProductAdditionServiceTest {

    private ProductAdditionRequest request = new ProductAdditionRequest("milk");

    @Mock
    private ProductAdditionValidator validator;

    @Mock
    private ProductBuilder productBuilder;

    @Mock
    private ProductRepository repository;

    @Mock
    private List<ShoppingListError> errors;

    @InjectMocks
    private ProductAdditionService productAdditionService = new ProductAdditionService();


    @Test
    public void shouldReturnResponseWithError() {
        Mockito.when(validator.validate(request)).thenReturn(errors);
        Mockito.when(errors.size()).thenReturn(1);
        ProductAdditionResponse response = productAdditionService.add(request);
        assertFalse(response.isSuccess());
        assertEquals(1, response.getErrors().size());
        Mockito.verifyZeroInteractions(repository);
    }

    @Test
    public void shouldReturnNoErrorsAndProductID() {
        Product product = Mockito.mock(Product.class);
        Mockito.when(validator.validate(request)).thenReturn(errors);
        Mockito.when(errors.isEmpty()).thenReturn(true);
        Mockito.when(productBuilder.buildInstance(request.getTitle())).thenReturn(product);
        Mockito.when(product.getId()).thenReturn(4L);
        ProductAdditionResponse response = productAdditionService.add(request);
        assertEquals(4L, (long) response.getId());
        assertTrue(response.isSuccess());
    }
}

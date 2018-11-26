package services.add;

import lv.java2.shopping_list.db.ProductRepository;
import lv.java2.shopping_list.domain.Product;
import lv.java2.shopping_list.domain.ProductFactory;
import lv.java2.shopping_list.services.Error;
import lv.java2.shopping_list.services.add.AddProductRequest;
import lv.java2.shopping_list.services.add.AddProductResponse;
import lv.java2.shopping_list.services.add.AddProductService;
import lv.java2.shopping_list.services.add.validation.AddProductValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class AddProductServiceTest {

    private AddProductRequest request;

    @Mock
    private AddProductValidator validator;

    @Mock
    private ProductRepository repository;

    @Mock
    private List<Error> errors;

    @Mock
    private ProductFactory productFactoryMock;

    @InjectMocks
    private AddProductService addProductService = new AddProductService();

    @Before
    public void init() {
        this.request = new AddProductRequest("milk");
    }


    @Test
    public void shouldReturnResponseWithError() {
        Mockito.when(errors.size()).thenReturn(1);
        Mockito.when(validator.validate(request)).thenReturn(errors);
        AddProductResponse response = addProductService.add(request);
        assertFalse(response.isSuccess());
        assertEquals(1, response.getErrors().size());
        Mockito.verifyZeroInteractions(repository);
    }

    @Test
    public void shouldReturnNoErrorsAndProductID() {
        Product productMock = Mockito.mock(Product.class);
        Mockito.when(validator.validate(request)).thenReturn(errors);
        Mockito.when(errors.isEmpty()).thenReturn(true);
        Mockito.when(productFactoryMock.createNewProductWithTitle(request.getTitle())).thenReturn(productMock);
        Mockito.when(repository.addToDataBase(productMock)).thenReturn(productMock);
        Mockito.when(productMock.getId()).thenReturn(4L);
        AddProductResponse response = addProductService.add(request);
        assertEquals(4L, (long) response.getProductId());
        assertTrue(response.isSuccess());
    }
}

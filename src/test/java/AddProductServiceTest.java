import db.jdbc.ProductRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import services.add.AddProductService;


import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddProductServiceTest {


    @Mock
    ProductRepositoryImpl productRepository;

    @InjectMocks
    AddProductService addProductService;


//    @Test
//    public void isAddToBaseOperationExecuted() {
//        addProductService.add("title");
//        Mockito.verify(productRepository, times(1)).addToDataBase(any());
//
//    }
}


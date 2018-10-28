import org.junit.Test;
import services.ProductPriceService;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static junit.framework.TestCase.assertEquals;

public class ProductPriceServiceTest {

    @Test
    public void shouldReturnZeroIfNegativePriceIsSet(){
        ProductPriceService service = new ProductPriceService();
        BigDecimal zero = new BigDecimal(0).setScale(2, RoundingMode.HALF_UP);
        assertEquals(zero, service.returnValidatedPrice(-2.00));
    }

}

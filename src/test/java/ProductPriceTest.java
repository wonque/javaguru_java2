import domain.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;

public class ProductPriceTest {

    private Product product;
    private BigDecimal valueToCompare;

    @Before
    public void init() {
        product = new Product();
    }

    @Test
    public void shouldReturn5IfPriceIsSetTo5() {
        product.setPrice(5.00);
        valueToCompare = setValueToCompare(5.00);
        assertEquals(valueToCompare, product.getPrice());
    }


    @Test
    public void shouldReturnZeroIfNegativePriceIsSet() {
        product.setPrice(-5.00);
        valueToCompare = setValueToCompare(0);
        assertEquals(valueToCompare, product.getPrice());
    }

    private BigDecimal setValueToCompare(double value) {
        return BigDecimal.valueOf(value).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}

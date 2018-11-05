package services;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProductPriceService {

    private BigDecimal decimalValue = new BigDecimal(0);

    public boolean isUserEnteredPriceBiggerThanZero(double price) {
        return price > 0;
    }

    public BigDecimal returnDecimalPrice(double value) {
        decimalValue = BigDecimal.valueOf(value);
        return decimalValue.setScale(2, RoundingMode.HALF_UP);
    }
}

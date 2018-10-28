package services;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProductPriceService {

    private BigDecimal decimalValue = new BigDecimal(0);

    private boolean isPriceNegative(double price) {
        return price < 0;
    }

    public BigDecimal returnValidatedPrice (double value) {
        if (isPriceNegative(value)) {
            return decimalValue.setScale(2,RoundingMode.HALF_UP);
        } else {
            decimalValue = BigDecimal.valueOf(value);
            return decimalValue.setScale(2, RoundingMode.HALF_UP);
        }
    }
}

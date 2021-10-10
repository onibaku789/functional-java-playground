package advanced.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BookDiscountCalculator {

    public static BigDecimal getOldBookDiscountedPrice(final BigDecimal price) {
        final BigDecimal newPrice;
        if (price.compareTo(BigDecimal.valueOf(1000)) > 0) {
            newPrice = price.multiply(BigDecimal.valueOf(.85));
        } else {
            newPrice = price.multiply(BigDecimal.valueOf(.75));
        }
        return newPrice.setScale(1, RoundingMode.HALF_EVEN);
    }

    public static BigDecimal getNewlyReleasedBookDiscountedPrice(final BigDecimal price) {
        final BigDecimal newPrice;
        if (price.compareTo(BigDecimal.valueOf(2000)) > 0) {
            newPrice = price.multiply(BigDecimal.valueOf(.60)).add(BigDecimal.valueOf(500));
        } else {
            newPrice = price.multiply(BigDecimal.valueOf(.75));
        }
        return newPrice.setScale(1, RoundingMode.HALF_EVEN);
    }

    public static BigDecimal getKidsBookDiscountedPrice(final BigDecimal price) {
        return price.multiply(BigDecimal.valueOf(0.5)).subtract(BigDecimal.ONE);
    }
}

package advanced;

import java.math.BigDecimal;
import java.util.function.Function;

public enum Type {
    KID(DefaultBookService::getKidsBookDiscountedPrice),
    NEW_RELEASE(DefaultBookService::getNewlyReleasedBookDiscountedPrice),
    OLD(DefaultBookService::getOldBookDiscountedPrice);
    private final Function<BigDecimal, BigDecimal> priceCalculator;

    Type(Function<BigDecimal, BigDecimal> priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

    public BigDecimal calculatePrice(BigDecimal price) {
        return priceCalculator.apply(price);
    }
}

package advanced;

import advanced.util.BookDiscountCalculator;

import java.math.BigDecimal;
import java.util.function.Function;

public enum Type {
    KID(BookDiscountCalculator::getKidsBookDiscountedPrice),
    NEW_RELEASE(BookDiscountCalculator::getNewlyReleasedBookDiscountedPrice),
    OLD(BookDiscountCalculator::getOldBookDiscountedPrice);
    private final Function<BigDecimal, BigDecimal> priceCalculator;

    Type(Function<BigDecimal, BigDecimal> priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

    public BigDecimal calculatePrice(BigDecimal price) {
        return priceCalculator.apply(price);
    }
}

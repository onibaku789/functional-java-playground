package advanced;

import java.math.BigDecimal;
import java.util.function.Function;

public enum Type {
    KID,
    NEW_RELEASE,
    OLD;

    public BigDecimal calculatePrice(Function<BigDecimal, BigDecimal> priceCalculator, BigDecimal price) {
        return priceCalculator.apply(price);
    }
}

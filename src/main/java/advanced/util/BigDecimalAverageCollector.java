package advanced.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class BigDecimalAverageCollector implements Collector<BigDecimal, BigDecimalAverageCollector.BigDecimalAccumulator, BigDecimal> {

    public static BigDecimalAverageCollector averagingBigDecimal() {
        return new BigDecimalAverageCollector();
    }

    @Override
    public Supplier<BigDecimalAccumulator> supplier() {
        return BigDecimalAccumulator::new;
    }

    @Override
    public BiConsumer<BigDecimalAccumulator, BigDecimal> accumulator() {
        return BigDecimalAccumulator::add;
    }

    @Override
    public BinaryOperator<BigDecimalAccumulator> combiner() {
        return BigDecimalAccumulator::combine;
    }

    @Override
    public Function<BigDecimalAccumulator, BigDecimal> finisher() {
        return BigDecimalAccumulator::average;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }


    static class BigDecimalAccumulator {
        private BigDecimal sum = BigDecimal.ZERO;
        private BigDecimal count = BigDecimal.ZERO;

        public BigDecimalAccumulator() {
        }

        public BigDecimalAccumulator(BigDecimal sum, BigDecimal count) {
            this.sum = sum;
            this.count = count;
        }

        BigDecimal average() {
            return BigDecimal.ZERO.compareTo(count) == 0 ?
                    BigDecimal.ZERO :
                    sum.divide(count, 2, RoundingMode.HALF_UP);
        }

        BigDecimalAccumulator combine(BigDecimalAccumulator another) {
            return new BigDecimalAccumulator(
                    sum.add(another.getSum()),
                    count.add(another.getCount()));
        }

        void add(BigDecimal successRate) {
            count = count.add(BigDecimal.ONE);
            sum = sum.add(successRate);
        }

        public BigDecimal getSum() {
            return sum;
        }

        public BigDecimal getCount() {
            return count;
        }
    }

}

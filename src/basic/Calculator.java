package basic;

import java.util.function.BiFunction;

public class Calculator {
    BiFunction<Double, Double, Double> add = Double::sum;
    BiFunction<Double, Double, Double> sub = (aDouble, aDouble2) -> aDouble - aDouble2;
    BiFunction<Double, Double, Double> mult = (aDouble, aDouble2) -> aDouble * aDouble2;
    BiFunction<Double, Double, Double> div = (aDouble, aDouble2) -> aDouble / aDouble2;

    public double add(double a, double b) {
        return calculate(a, b, add);
    }

    public double sub(double a, double b) {
        return calculate(a, b, sub);
    }

    public double mult(double a, double b) {
        return calculate(a, b, mult);
    }

    public double div(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException();
        }
        return calculate(a, b, div);
    }

    private double calculate(double a, double b, BiFunction<Double, Double, Double> function) {
        return function.apply(a, b);
    }
}

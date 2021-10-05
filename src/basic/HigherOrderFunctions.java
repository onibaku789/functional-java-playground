package basic;

import java.util.stream.IntStream;

public class HigherOrderFunctions {
    private final Basic basic;

    public HigherOrderFunctions(Basic basic) {
        this.basic = basic;
    }

    public static void main(String[] args) {
        HigherOrderFunctions higherOrderFunctions = new HigherOrderFunctions(new Basic());
        higherOrderFunctions.printFirstTenOddNumber();
    }

    public void printFirstTenOddNumber() {
        IntStream.range(0, 10)
                .filter(basic::isOdd1)
                .forEach(System.out::println);
    }
}

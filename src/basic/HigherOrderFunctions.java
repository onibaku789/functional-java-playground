package basic;

import java.util.List;
import java.util.stream.Collectors;
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
        List<Integer> numbers = IntStream.range(0, 10)
                .boxed()
                .filter(basic::isOdd1)
                .collect(Collectors.toList());
        System.out.println("numbers = " + numbers);
    }
}

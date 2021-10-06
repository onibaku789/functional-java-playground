package basic;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SuppressWarnings({"Convert2Lambda", "Convert2Diamond"})
public class HigherOrderFunctions {

    public HigherOrderFunctions() {
    }

    public static void main(String[] args) {
        HigherOrderFunctions higherOrderFunctions = new HigherOrderFunctions();
        higherOrderFunctions.printFirstTenNumbersByFilter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer input) {
                return input % 2 != 1;
            }
        });
        higherOrderFunctions.printFirstTenNumbersByFilter((Integer input) -> input % 2 == 1);
        higherOrderFunctions.printFirstTenNumbersByFilter(input -> true);
    }

    public void printFirstTenNumbersByFilter(Predicate<Integer> filter) {
        List<Integer> numbers = IntStream.range(0, 10)
                .boxed()
                .filter(filter)
                .collect(Collectors.toList());
        System.out.println("numbers = " + numbers);
    }
}

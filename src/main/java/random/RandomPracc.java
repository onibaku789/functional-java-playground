package random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@SuppressWarnings({"Convert2Lambda", "Anonymous2MethodRef", "Convert2Diamond", "Java8ListSort"})
public class RandomPracc {

    private final Random random = new Random(1);

    public void java() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add(random.nextInt(100 - 1) + 1);
        }
        System.out.println("numbers = " + numbers);
        Collections.sort(numbers, new Comparator<Integer>() {
            @Override
            public int compare(Integer n1, Integer n2) {
                return n1.compareTo(n2);
            }
        });
        System.out.println("numbers = " + numbers);
    }

    public void java8() {
        List<Integer> numbers = random.ints(1, 101)
                .limit(10)
                .boxed()
                .collect(Collectors.toList());
        System.out.println("numbers = " + numbers);
        List<Integer> sortedNumbers = numbers.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        System.out.println("numbers = " + sortedNumbers);
    }

}

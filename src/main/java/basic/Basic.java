package basic;

@SuppressWarnings({"Convert2Lambda"})
public class Basic {
    public boolean isOdd1(int input) {
        BasicInterface isOdd = new BasicInterface() {
            @Override
            public boolean test(int input) {
                return input % 2 != 0;
            }
        };
        return isOdd.test(input);
    }

    public boolean isOdd2(int input) {
        BasicInterface isOdd = (int lambdaInput) -> {
            return lambdaInput % 2 != 0;
        };
        return isOdd.test(input);
    }

    public boolean isOdd3(int input) {
        BasicInterface isOdd3 = this::isOdd;
        return isOdd3.test(input);
    }

    private boolean isOdd(int input) {
        return input % 2 != 0;
    }

    @FunctionalInterface
    public interface BasicInterface {
        boolean test(int input);
    }
}

package template.java8;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Network {
    private final String userName;
    private final String password;

    Network(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * Publish the data to whatever network.
     */
    public boolean post(String message,
                        BiFunction<String, String, Boolean> login,
                        Function<byte[], Boolean> sendData,
                        Runnable logOut) {
        // Authenticate before posting. Every network uses a different
        // authentication method.
        if (login.apply(this.userName, this.password)) {
            // Send the post data.
            boolean result = sendData.apply(message.getBytes());
            logOut.run();
            return result;
        }
        return false;
    }


}
